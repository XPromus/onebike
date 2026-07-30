package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.CupFilter
import com.xpromus.onebike_backend.cup.specification.CupSpecification
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
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
class CupSpecificationTest {

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
    private lateinit var cupRepository: CupRepository
    @Autowired
    private lateinit var nationRepository: NationRepository

    private fun saveNation(): Nation {
        return nationRepository.save(
            Nation(
                longName = "Germany",
                shortName = "GER",
                flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
            )
        )
    }

    private fun saveCups(): List<Cup> {
        val nation = saveNation()
        return listOf(
            cupRepository.save(
                Cup(
                    cupName = "Lausitzcup",
                    url = "https://www.google.de",
                    races = mutableListOf(),
                    nation = nation
                ),
            ),
            cupRepository.save(
                Cup(
                    cupName = "Tour de Germany",
                    url = "https://www.google.de",
                    races = mutableListOf(),
                    nation = nation
                ),
            ),
            cupRepository.save(
                Cup(
                    cupName = "Tour de Denmark",
                    url = "https://www.google.de",
                    races = mutableListOf(),
                    nation = nation
                ),
            )
        )
    }

    @BeforeEach
    fun setup() {
        cupRepository.deleteAll()
        nationRepository.deleteAll()
    }

    @Test
    fun `all null filter returns all cups`() {
        saveCups()

        val filter = CupFilter(id = null, cupName = null)
        val spec = CupSpecification.withFilter(filter)
        val result = cupRepository.findAll(spec)

        assertEquals(3, result.size)
    }

    @Test
    fun `filters by id`() {
        val cups = saveCups()
        val lausitzCup = cups[0]

        val filter = CupFilter(id = lausitzCup.id, cupName = null)
        val spec = CupSpecification.withFilter(filter)
        val result = cupRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitzcup", result[0].cupName)
    }

    @Test
    fun `filters by partial cupName case-insensitive`() {
        saveCups()

        val filter = CupFilter(id = null, cupName = "lau")
        val spec = CupSpecification.withFilter(filter)
        val result = cupRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitzcup", result[0].cupName)
    }

    @Test
    fun `combines multiple filter fields`() {
        val cups = saveCups()
        val lausitzCup = cups[0]

        val filter = CupFilter(
            id = lausitzCup.id,
            cupName = "lausitzcup"
        )
        val spec = CupSpecification.withFilter(filter)
        val result = cupRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitzcup", result[0].cupName)
    }

    @Test
    fun `no match returns empty`() {
        saveCups()

        val filter = CupFilter(id = null, cupName = "zzzzz")
        val spec = CupSpecification.withFilter(filter)
        val result = cupRepository.findAll(spec)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `like works with uppercase input`() {
        saveCups()

        val filter = CupFilter(id = null, cupName = "LAU")
        val spec = CupSpecification.withFilter(filter)
        val result = cupRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitzcup", result[0].cupName)
    }

}
