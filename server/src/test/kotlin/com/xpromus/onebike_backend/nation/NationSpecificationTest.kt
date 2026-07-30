package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.nation.dto.NationFilter
import com.xpromus.onebike_backend.nation.specification.NationSpecification
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class NationSpecificationTest {

    companion object {
        @Container
        @JvmStatic
        val postgres = PostgreSQLContainer(
            DockerImageName.parse("postgres:16-alpine")
        )

        @DynamicPropertySource
        @JvmStatic
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
        }
    }

    @Autowired
    private lateinit var nationRepository: NationRepository

    @BeforeEach
    fun setup() {
        nationRepository.deleteAll()
    }

    private fun saveNations(): List<Nation> {
        return listOf(
            nationRepository.save(Nation(longName = "Germany", shortName = "GER", flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA")),
            nationRepository.save(Nation(longName = "Spain", shortName = "ESP", flagEmoji = "\uD83C\uDDEA\uD83C\uDDF8")),
            nationRepository.save(Nation(longName = "France", shortName = "FRA", flagEmoji = "\uD83C\uDDEB\uD83C\uDDF7")),
        )
    }

    @Test
    fun `all null filter returns all nations`() {
        saveNations()

        val filter = NationFilter(id = null, longName = null, shortName = null)
        val spec = NationSpecification.withFilter(filter)
        val result = nationRepository.findAll(spec)

        assertEquals(3, result.size)
    }

    @Test
    fun `filters by id`() {
        val nations = saveNations()
        val spain = nations[1]

        val filter = NationFilter(id = spain.id, longName = null, shortName = null)
        val spec = NationSpecification.withFilter(filter)
        val result = nationRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Spain", result[0].longName)
    }

    @Test
    fun `filters by partial longName case-insensitive`() {
        saveNations()

        val filter = NationFilter(id = null, longName = "er", shortName = null)
        val spec = NationSpecification.withFilter(filter)
        val result = nationRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Germany", result[0].longName)
    }

    @Test
    fun `filters by partial shortName`() {
        saveNations()

        val filter = NationFilter(id = null, longName = null, shortName = "fra")
        val spec = NationSpecification.withFilter(filter)
        val result = nationRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("FRA", result[0].shortName)
    }

    @Test
    fun `combines multiple filter fields`() {
        val nations = saveNations()
        val germany = nations[0]

        val filter = NationFilter(
            id = germany.id,
            longName = "germany",
            shortName = "ger",
        )
        val spec = NationSpecification.withFilter(filter)
        val result = nationRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Germany", result[0].longName)
    }

    @Test
    fun `no match returns empty`() {
        saveNations()

        val filter = NationFilter(id = null, longName = "zzzzz", shortName = null)
        val spec = NationSpecification.withFilter(filter)
        val result = nationRepository.findAll(spec)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `like works with uppercase input`() {
        saveNations()

        val filter = NationFilter(id = null, longName = "GER", shortName = null)
        val spec = NationSpecification.withFilter(filter)
        val result = nationRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Germany", result[0].longName)
    }

    @Test
    fun `like works with lowercase input`() {
        saveNations()

        val filter = NationFilter(id = null, longName = "germany", shortName = null)
        val spec = NationSpecification.withFilter(filter)
        val result = nationRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Germany", result[0].longName)
    }
}
