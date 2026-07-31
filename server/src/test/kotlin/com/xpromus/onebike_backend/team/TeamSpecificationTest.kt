package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.team.dto.TeamFilter
import com.xpromus.onebike_backend.team.specification.TeamSpecification
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
class TeamSpecificationTest {

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
    private lateinit var teamRepository: TeamRepository
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

    private fun saveTeams(
        nation: Nation
    ): List<Team> {
        return listOf(
            teamRepository.save(
                Team(
                    teamName = "Post SV Görlitz",
                    shortName = "PSG",
                    teamDescription = "Team from Görlitz",
                    nation = nation
                )
            ),
            teamRepository.save(
                Team(
                    teamName = "Team Deutschland",
                    shortName = "TD",
                    teamDescription = "Team from Germany",
                    nation = nation
                )
            ),
            teamRepository.save(
                Team(
                    teamName = "Team Berlin",
                    shortName = "TB",
                    teamDescription = "Team from Berlin",
                    nation = nation
                )
            ),
        )
    }

    @BeforeEach
    fun setup() {
        teamRepository.deleteAll()
        nationRepository.deleteAll()
    }

    @Test
    fun `all null filter returns all teams`() {
        val nation = saveNation()
        saveTeams(nation)

        val filter = TeamFilter(id = null, teamName = null, shortName = null)
        val spec = TeamSpecification.withFilter(filter)
        val result = teamRepository.findAll(spec)

        assertEquals(3, result.size)
    }

    @Test
    fun `filters by id`() {
        val nation = saveNation()
        val teams = saveTeams(nation)
        val psg = teams[0]

        val filter = TeamFilter(id = psg.id, teamName = null, shortName = null)
        val spec = TeamSpecification.withFilter(filter)
        val result = teamRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Post SV Görlitz", result[0].teamName)
    }

    @Test
    fun `filters by partial teamName case-insensitive`() {
        val nation = saveNation()
        saveTeams(nation)

        val filter = TeamFilter(id = null, teamName = "deutschland", shortName = null)
        val spec = TeamSpecification.withFilter(filter)
        val result = teamRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Team Deutschland", result[0].teamName)
    }

    @Test
    fun `filters by partial shortName case-insensitive`() {
        val nation = saveNation()
        saveTeams(nation)

        val filter = TeamFilter(id = null, teamName = null, shortName = "psg")
        val spec = TeamSpecification.withFilter(filter)
        val result = teamRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("PSG", result[0].shortName)
    }

    @Test
    fun `combines teamName and shortName`() {
        val nation = saveNation()
        saveTeams(nation)

        val filter = TeamFilter(id = null, teamName = "berlin", shortName = "tb")
        val spec = TeamSpecification.withFilter(filter)
        val result = teamRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Team Berlin", result[0].teamName)
    }

    @Test
    fun `combines id and teamName`() {
        val nation = saveNation()
        val teams = saveTeams(nation)
        val psg = teams[0]

        val filter = TeamFilter(id = psg.id, teamName = "post", shortName = null)
        val spec = TeamSpecification.withFilter(filter)
        val result = teamRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Post SV Görlitz", result[0].teamName)
    }

    @Test
    fun `no match returns empty`() {
        val nation = saveNation()
        saveTeams(nation)

        val filter = TeamFilter(id = null, teamName = "zzzzz", shortName = null)
        val spec = TeamSpecification.withFilter(filter)
        val result = teamRepository.findAll(spec)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `like works with uppercase input`() {
        val nation = saveNation()
        saveTeams(nation)

        val filter = TeamFilter(id = null, teamName = "BERLIN", shortName = null)
        val spec = TeamSpecification.withFilter(filter)
        val result = teamRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Team Berlin", result[0].teamName)
    }

    @Test
    fun `like works with lowercase input`() {
        val nation = saveNation()
        saveTeams(nation)

        val filter = TeamFilter(id = null, teamName = "berlin", shortName = null)
        val spec = TeamSpecification.withFilter(filter)
        val result = teamRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Team Berlin", result[0].teamName)
    }
}
