package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.race.dto.RaceFilter
import com.xpromus.onebike_backend.race.specification.RaceSpecification
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
class RaceSpecificationTest {

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
    private lateinit var raceRepository: RaceRepository
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

    private fun saveRaces(
        nation: Nation
    ): List<Race> {
        return listOf(
            raceRepository.save(
                Race(
                    raceName = "Lausitz Marathon",
                    lengthInKm = 120.0f,
                    raceDate = LocalDate.of(2026, 6, 1),
                    startTime = Instant.ofEpochSecond(1700000000),
                    nation = nation
                )
            ),
            raceRepository.save(
                Race(
                    raceName = "Tour de Germany",
                    lengthInKm = 180.0f,
                    raceDate = LocalDate.of(2026, 7, 1),
                    startTime = Instant.ofEpochSecond(1700000100),
                    nation = nation
                )
            ),
            raceRepository.save(
                Race(
                    raceName = "Tour de Denmark",
                    lengthInKm = 150.0f,
                    raceDate = LocalDate.of(2026, 8, 1),
                    startTime = Instant.ofEpochSecond(1700000200),
                    nation = nation
                )
            ),
        )
    }

    @BeforeEach
    fun setup() {
        raceRepository.deleteAll()
        nationRepository.deleteAll()
    }

    @Test
    fun `all null filter returns all races`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(3, result.size)
    }

    @Test
    fun `filters by id`() {
        val nation = saveNation()
        val races = saveRaces(nation)
        val lausitz = races[0]

        val filter = RaceFilter(
            id = lausitz.id,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitz Marathon", result[0].raceName)
    }

    @Test
    fun `filters by partial raceName case-insensitive`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = "marathon",
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitz Marathon", result[0].raceName)
    }

    @Test
    fun `filters by exact lengthInKm`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = 150.0f,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Tour de Denmark", result[0].raceName)
    }

    @Test
    fun `filters by minLengthInKm`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = 160.0f,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Tour de Germany", result[0].raceName)
    }

    @Test
    fun `filters by maxLengthInKm`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = 140.0f,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitz Marathon", result[0].raceName)
    }

    @Test
    fun `filters by lengthInKm range`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = 130.0f,
            maxLengthInKm = 170.0f,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Tour de Denmark", result[0].raceName)
    }

    @Test
    fun `filters by exact raceDate`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = LocalDate.of(2026, 6, 1),
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitz Marathon", result[0].raceName)
    }

    @Test
    fun `filters by minRaceDate`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = LocalDate.of(2026, 7, 1),
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(2, result.size)
    }

    @Test
    fun `filters by maxRaceDate`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = LocalDate.of(2026, 7, 1),
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(2, result.size)
    }

    @Test
    fun `filters by raceDate range`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = LocalDate.of(2026, 6, 15),
            maxRaceDate = LocalDate.of(2026, 7, 15),
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Tour de Germany", result[0].raceName)
    }

    @Test
    fun `filters by exact startTime`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = Instant.ofEpochSecond(1700000100),
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Tour de Germany", result[0].raceName)
    }

    @Test
    fun `filters by minStartTime`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = Instant.ofEpochSecond(1700000050),
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(2, result.size)
    }

    @Test
    fun `filters by maxStartTime`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = null,
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = Instant.ofEpochSecond(1700000050),
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitz Marathon", result[0].raceName)
    }

    @Test
    fun `combines multiple filter fields`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = "germany",
            lengthInKm = 180.0f,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = LocalDate.of(2026, 7, 1),
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Tour de Germany", result[0].raceName)
    }

    @Test
    fun `no match returns empty`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = "zzzzz",
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `like works with uppercase input`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = "LAUSITZ",
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitz Marathon", result[0].raceName)
    }

    @Test
    fun `like works with lowercase input`() {
        val nation = saveNation()
        saveRaces(nation)

        val filter = RaceFilter(
            id = null,
            raceName = "lausitz",
            lengthInKm = null,
            minLengthInKm = null,
            maxLengthInKm = null,
            raceDate = null,
            minRaceDate = null,
            maxRaceDate = null,
            startTime = null,
            minStartTime = null,
            maxStartTime = null,
        )
        val spec = RaceSpecification.withFilter(filter)
        val result = raceRepository.findAll(spec)

        assertEquals(1, result.size)
        assertEquals("Lausitz Marathon", result[0].raceName)
    }
}
