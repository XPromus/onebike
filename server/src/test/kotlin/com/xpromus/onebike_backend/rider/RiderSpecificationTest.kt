package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.rider.dto.RiderFilter
import com.xpromus.onebike_backend.rider.specification.RiderSpecification
import com.xpromus.onebike_backend.team.Team
import com.xpromus.onebike_backend.team.TeamRepository
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
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class RiderSpecificationTest {

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
    private lateinit var riderRepository: RiderRepository
    @Autowired
    private lateinit var nationRepository: NationRepository
    @Autowired
    private lateinit var teamRepository: TeamRepository

    private fun saveNation(): Nation {
        return nationRepository.save(
            Nation(
                longName = "Germany",
                shortName = "GER",
                flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
            )
        )
    }

    private fun saveTeam(
        nation: Nation
    ): Team {
        return teamRepository.save(
            Team(
                teamName = "Team 1",
                shortName = "T1",
                teamDescription = "Test Team 1",
                nation = nation
            )
        )
    }

    private fun saveRider(
        nation: Nation,
        team: Team
    ): List<Rider> {
        return listOf(
            riderRepository.save(
                Rider(
                    firstName = "Test",
                    lastName = "McTest",
                    dateOfBirth = LocalDate.of(2000, 1, 15),
                    nation = nation,
                    team = team
                )
            ),
            riderRepository.save(
                Rider(
                    firstName = "Jupiter",
                    lastName = "Rider",
                    dateOfBirth = LocalDate.of(2002, 6, 10),
                    nation = nation,
                    team = team
                )
            ),
            riderRepository.save(
                Rider(
                    firstName = "Kotlin",
                    lastName = "Unit",
                    dateOfBirth = LocalDate.of(1998, 12, 5),
                    nation = nation,
                    team = null
                )
            ),
        )
    }

    @BeforeEach
    fun setup() {
        teamRepository.deleteAll()
        riderRepository.deleteAll()
        nationRepository.deleteAll()
    }

    @Test
    fun `all null filter returns all riders`() {
        val nation = saveNation()
        val riders = saveRider(
            nation,
            saveTeam(nation)

        )

        val filter = RiderFilter(id = null, firstName = null, lastName = null, dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)

        assertEquals(3, result.size)
    }

    @Test
    fun `filters by id`() {
        val nation = saveNation()
        val riders = saveRider(
            nation,
            saveTeam(nation)
        )
        val testRider = riders[0]
        val filter = RiderFilter(id = testRider.id, firstName = null, lastName = null, dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Test", result[0].firstName)
    }

    @Test
    fun `filters by partial firstName case-insensitive`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = "test", lastName = null, dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Test", result[0].firstName)
    }

    @Test
    fun `filters by partial lastName case-insensitive`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = null, lastName = "unit", dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Kotlin", result[0].firstName)
    }

    @Test
    fun `combines firstName and lastName`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = "jupiter", lastName = "rider", dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Jupiter", result[0].firstName)
    }

    @Test
    fun `combines id and name`() {
        val nation = saveNation()
        val riders = saveRider(nation, saveTeam(nation))
        val kotlinRider = riders[2]
        val filter = RiderFilter(id = kotlinRider.id, firstName = null, lastName = "unit", dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Kotlin", result[0].firstName)
    }

    @Test
    fun `no match returns empty`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = "zzzzz", lastName = null, dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `like works with uppercase input`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = "KOTLIN", lastName = null, dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Kotlin", result[0].firstName)
    }

    @Test
    fun `like works with lowercase input`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = "kotlin", lastName = null, dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Kotlin", result[0].firstName)
    }

    @Test
    fun `filters by exact dateOfBirth`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = null, lastName = null, dateOfBirth = LocalDate.of(2000, 1, 15), minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Test", result[0].firstName)
    }

    @Test
    fun `filters by minDateOfBirth`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = null, lastName = null, dateOfBirth = null, minDateOfBirth = LocalDate.of(2000, 1, 1), maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(2, result.size)
    }

    @Test
    fun `filters by maxDateOfBirth`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = null, lastName = null, dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = LocalDate.of(1999, 12, 31))
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Kotlin", result[0].firstName)
    }

    @Test
    fun `filters by dateOfBirth range`() {
        val nation = saveNation()
        saveRider(nation, saveTeam(nation))
        val filter = RiderFilter(id = null, firstName = null, lastName = null, dateOfBirth = null, minDateOfBirth = LocalDate.of(2000, 1, 1), maxDateOfBirth = LocalDate.of(2003, 1, 1))
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(2, result.size)
    }

    @Test
    fun `filters by id with null team attribute`() {
        val nation = saveNation()
        val riders = saveRider(nation, saveTeam(nation))
        val kotlinRider = riders[2]
        val filter = RiderFilter(id = kotlinRider.id, firstName = null, lastName = null, dateOfBirth = null, minDateOfBirth = null, maxDateOfBirth = null)
        val spec = RiderSpecification.withFilter(filter)
        val result = riderRepository.findAll(spec)
        assertEquals(1, result.size)
        assertEquals("Kotlin", result[0].firstName)
    }
}
