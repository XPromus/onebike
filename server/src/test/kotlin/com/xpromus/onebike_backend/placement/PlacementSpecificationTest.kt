package com.xpromus.onebike_backend.placement

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.placement.dto.PlacementFilter
import com.xpromus.onebike_backend.placement.specification.PlacementSpecification
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.RaceRepository
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.RiderRepository
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
import java.time.Instant
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class PlacementSpecificationTest {

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
    private lateinit var placementRepository: PlacementRepository
    @Autowired
    private lateinit var riderRepository: RiderRepository
    @Autowired
    private lateinit var raceRepository: RaceRepository
    @Autowired
    private lateinit var nationRepository: NationRepository
    @Autowired
    private lateinit var teamRepository: TeamRepository

    private data class TestData(
        val rider1: Rider,
        val rider2: Rider,
        val race1: Race,
        val race2: Race,
        val placements: List<Placement>,
    )

    private fun nullFilter(): PlacementFilter = PlacementFilter(
        id = null,
        points = null,
        minPoints = null,
        maxPoints = null,
        finishTimeInSeconds = null,
        minFinishTimeInSeconds = null,
        maxFinishTimeInSeconds = null,
        finishStatus = null,
    )

    private fun saveNation(): Nation {
        return nationRepository.save(
            Nation(
                longName = "Germany",
                shortName = "GER",
                flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
            )
        )
    }

    private fun saveTeam(nation: Nation): Team {
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
        team: Team,
        firstName: String,
        lastName: String
    ): Rider {
        return riderRepository.save(
            Rider(
                firstName = firstName,
                lastName = lastName,
                dateOfBirth = LocalDate.of(2000, 1, 15),
                nation = nation,
                team = team
            )
        )
    }

    private fun saveRace(nation: Nation, raceName: String): Race {
        return raceRepository.save(
            Race(
                raceName = raceName,
                lengthInKm = 150.0f,
                raceDate = LocalDate.of(2026, 6, 1),
                startTime = Instant.ofEpochSecond(1700000000),
                nation = nation
            )
        )
    }

    private fun saveTestData(): TestData {
        val nation = saveNation()
        val team = saveTeam(nation)
        val rider1 = saveRider(nation, team, "Test", "McTest")
        val rider2 = saveRider(nation, team, "Jupiter", "Rider")
        val race1 = saveRace(nation, "Lausitz Marathon")
        val race2 = saveRace(nation, "Tour de Germany")

        val placements = listOf(
            placementRepository.save(
                Placement(
                    points = 25,
                    finishTimeInSeconds = 3600,
                    finishStatus = "finished",
                    rider = rider1,
                    race = race1
                )
            ),
            placementRepository.save(
                Placement(
                    points = 18,
                    finishTimeInSeconds = 3650,
                    finishStatus = "DNF",
                    rider = rider2,
                    race = race1
                )
            ),
            placementRepository.save(
                Placement(
                    points = 0,
                    finishTimeInSeconds = 0,
                    finishStatus = "DNS",
                    rider = rider1,
                    race = race2
                )
            ),
        )

        return TestData(
            rider1 = rider1,
            rider2 = rider2,
            race1 = race1,
            race2 = race2,
            placements = placements,
        )
    }

    @BeforeEach
    fun setup() {
        placementRepository.deleteAll()
        riderRepository.deleteAll()
        teamRepository.deleteAll()
        raceRepository.deleteAll()
        nationRepository.deleteAll()
    }

    @Test
    fun `all null filter returns all placements`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter())
        val result = placementRepository.findAll(spec)

        assertEquals(3, result.size)
    }

    @Test
    fun `filters by id`() {
        val data = saveTestData()
        val firstPlacement = data.placements[0]

        val spec = PlacementSpecification.withFilter(nullFilter().copy(id = firstPlacement.id))
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals(25, result[0].points)
    }

    @Test
    fun `filters by exact points`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(points = 18))
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("DNF", result[0].finishStatus)
    }

    @Test
    fun `filters by minPoints`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(minPoints = 20))
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals(25, result[0].points)
    }

    @Test
    fun `filters by maxPoints`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(maxPoints = 20))
        val result = placementRepository.findAll(spec)

        assertEquals(2, result.size)
    }

    @Test
    fun `filters by points range`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(minPoints = 10, maxPoints = 20))
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals(18, result[0].points)
    }

    @Test
    fun `filters by exact finishTimeInSeconds`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(finishTimeInSeconds = 3650))
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("DNF", result[0].finishStatus)
    }

    @Test
    fun `filters by minFinishTimeInSeconds`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(minFinishTimeInSeconds = 3625))
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals(3650, result[0].finishTimeInSeconds)
    }

    @Test
    fun `filters by maxFinishTimeInSeconds`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(maxFinishTimeInSeconds = 3625))
        val result = placementRepository.findAll(spec)

        assertEquals(2, result.size)
    }

    @Test
    fun `filters by partial finishStatus case-insensitive`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(finishStatus = "dnf"))
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("DNF", result[0].finishStatus)
    }

    @Test
    fun `combines multiple filter fields`() {
        val data = saveTestData()
        val firstPlacement = data.placements[0]

        val spec = PlacementSpecification.withFilter(
            nullFilter().copy(id = firstPlacement.id, points = 25, finishStatus = "finished")
        )
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("finished", result[0].finishStatus)
    }

    @Test
    fun `no match returns empty`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(points = 999))
        val result = placementRepository.findAll(spec)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `like works with uppercase input`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(finishStatus = "FINISHED"))
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("finished", result[0].finishStatus)
    }

    @Test
    fun `like works with lowercase input`() {
        saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter().copy(finishStatus = "finished"))
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("finished", result[0].finishStatus)
    }

    @Test
    fun `filters by raceId`() {
        val data = saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter(), raceId = data.race1.id)
        val result = placementRepository.findAll(spec)

        assertEquals(2, result.size)
    }

    @Test
    fun `filters by riderId`() {
        val data = saveTestData()

        val spec = PlacementSpecification.withFilter(nullFilter(), riderId = data.rider1.id)
        val result = placementRepository.findAll(spec)

        assertEquals(2, result.size)
    }

    @Test
    fun `combines raceId and points filter`() {
        val data = saveTestData()

        val spec = PlacementSpecification.withFilter(
            nullFilter().copy(points = 25),
            raceId = data.race1.id
        )
        val result = placementRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("finished", result[0].finishStatus)
    }
}
