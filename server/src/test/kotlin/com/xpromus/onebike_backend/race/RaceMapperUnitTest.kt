package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.dto.CupDescriptorDto
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.placement.Placement
import com.xpromus.onebike_backend.placement.dto.PlacementDescriptorDto
import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.GetRaceWithChildrenDto
import com.xpromus.onebike_backend.race.dto.PostRaceDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto
import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto
import com.xpromus.onebike_backend.race.mapper.toEntity
import com.xpromus.onebike_backend.race.mapper.toGetRaceDto
import com.xpromus.onebike_backend.race.mapper.toGetRaceWithChildrenDto
import com.xpromus.onebike_backend.race.mapper.toNewEntity
import com.xpromus.onebike_backend.race.mapper.toRaceDescriptorDto
import com.xpromus.onebike_backend.rider.Rider
import java.time.Instant
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class RaceMapperUnitTest {

    private val nation = Nation(
        id = 5L,
        longName = "Germany",
        shortName = "GER",
        flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
    )

    private val updatedNation = Nation(
        id = 7L,
        longName = "Spain",
        shortName = "ESP",
        flagEmoji = "\uD83C\uDDEA\uD83C\uDDF8",
    )

    private val cup = Cup(
        id = 7L,
        cupName = "Lausitzcup",
        url = "https://www.lausitzcup.de",
        nation = nation,
    )

    private val placements: MutableList<Placement> = mutableListOf()

    private val race = Race(
        id = 1L,
        raceName = "Lausitz Marathon",
        lengthInKm = 120.0f,
        raceDate = LocalDate.of(2026, 6, 1),
        startTime = Instant.ofEpochSecond(1700000000),
        nation = nation,
        cup = cup,
    )

    init {
        val rider = Rider(
            id = 2L,
            firstName = "Test",
            lastName = "McTest",
            dateOfBirth = LocalDate.of(2000, 1, 15),
            nation = nation,
            team = null,
        )
        placements.add(
            Placement(
                id = 8L,
                points = 25,
                finishTimeInSeconds = 3600,
                finishStatus = "finished",
                rider = rider,
                race = race,
            )
        )
        race.placements = placements
    }

    @Test
    fun `toGetRaceDto maps all fields with cupId`() {
        val expected = GetRaceDto(
            id = 1L,
            raceName = "Lausitz Marathon",
            lengthInKm = 120.0f,
            raceDate = LocalDate.of(2026, 6, 1),
            startTime = Instant.ofEpochSecond(1700000000),
            nationId = 5L,
            cupId = 7L,
            placementIds = listOf(8L),
        )

        val actual = race.toGetRaceDto(
            placementIds = listOf(8L),
            nationId = 5L,
            cupId = 7L,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetRaceDto maps cupId as null`() {
        val expected = GetRaceDto(
            id = 1L,
            raceName = "Lausitz Marathon",
            lengthInKm = 120.0f,
            raceDate = LocalDate.of(2026, 6, 1),
            startTime = Instant.ofEpochSecond(1700000000),
            nationId = 5L,
            cupId = null,
            placementIds = emptyList(),
        )

        val actual = race.toGetRaceDto(
            placementIds = emptyList(),
            nationId = 5L,
            cupId = null,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetRaceWithChildrenDto maps all fields`() {
        val placementDescriptors = listOf(
            PlacementDescriptorDto(
                id = 8L,
                points = 25,
                finishTimeInSeconds = 3600,
                finishStatus = "finished",
                raceId = 1L,
            ),
        )
        val nationDescriptor = NationDescriptorDto(
            id = 5L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
        )
        val cupDescriptor = CupDescriptorDto(
            id = 7L,
            cupName = "Lausitzcup",
            url = "https://www.lausitzcup.de",
        )

        val expected = GetRaceWithChildrenDto(
            id = 1L,
            raceName = "Lausitz Marathon",
            lengthInKm = 120.0f,
            raceDate = LocalDate.of(2026, 6, 1),
            startTime = Instant.ofEpochSecond(1700000000),
            nation = nationDescriptor,
            cup = cupDescriptor,
            placements = placementDescriptors,
        )

        val actual = race.toGetRaceWithChildrenDto(
            placements = placementDescriptors,
            nation = nationDescriptor,
            cup = cupDescriptor,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetRaceWithChildrenDto maps cup as null`() {
        val nationDescriptor = NationDescriptorDto(
            id = 5L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
        )

        val expected = GetRaceWithChildrenDto(
            id = 1L,
            raceName = "Lausitz Marathon",
            lengthInKm = 120.0f,
            raceDate = LocalDate.of(2026, 6, 1),
            startTime = Instant.ofEpochSecond(1700000000),
            nation = nationDescriptor,
            cup = null,
            placements = emptyList(),
        )

        val actual = race.toGetRaceWithChildrenDto(
            placements = emptyList(),
            nation = nationDescriptor,
            cup = null,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toRaceDescriptorDto maps all fields`() {
        val expected = RaceDescriptorDto(
            id = 1L,
            raceName = "Lausitz Marathon",
            lengthInKm = 120.0f,
            raceDate = LocalDate.of(2026, 6, 1),
            startTime = Instant.ofEpochSecond(1700000000),
        )

        assertEquals(expected, race.toRaceDescriptorDto())
    }

    @Test
    fun `PutRaceDto toEntity preserves id and placements and maps fields`() {
        val put = PutRaceDto(
            raceName = "Tour de Germany",
            lengthInKm = 180.0f,
            raceDate = LocalDate.of(2026, 7, 1),
            startTime = Instant.ofEpochSecond(1700000100),
            nationId = 7L,
            cupId = null,
        )

        val result = put.toEntity(original = race, nation = updatedNation, cup = null)

        assertEquals(1L, result.id)
        assertSame(placements, result.placements)
        assertEquals("Tour de Germany", result.raceName)
        assertEquals(180.0f, result.lengthInKm)
        assertEquals(LocalDate.of(2026, 7, 1), result.raceDate)
        assertEquals(Instant.ofEpochSecond(1700000100), result.startTime)
        assertSame(updatedNation, result.nation)
        assertEquals(null, result.cup)
    }

    @Test
    fun `PutRaceDto toNewEntity creates entity without id`() {
        val put = PutRaceDto(
            raceName = "Tour de Germany",
            lengthInKm = 180.0f,
            raceDate = LocalDate.of(2026, 7, 1),
            startTime = Instant.ofEpochSecond(1700000100),
            nationId = 5L,
            cupId = 7L,
        )

        val result = put.toNewEntity(nation = nation, cup = cup)

        assertEquals(null, result.id)
        assertEquals("Tour de Germany", result.raceName)
        assertEquals(180.0f, result.lengthInKm)
        assertEquals(LocalDate.of(2026, 7, 1), result.raceDate)
        assertEquals(Instant.ofEpochSecond(1700000100), result.startTime)
        assertSame(nation, result.nation)
        assertSame(cup, result.cup)
    }

    @Test
    fun `PostRaceDto toNewEntity creates entity without id`() {
        val post = PostRaceDto(
            raceName = "Tour de Denmark",
            lengthInKm = 150.0f,
            raceDate = LocalDate.of(2026, 8, 1),
            startTime = Instant.ofEpochSecond(1700000200),
            nationId = 5L,
            cupId = null,
        )

        val result = post.toNewEntity(nation = nation, cup = null)

        assertEquals(null, result.id)
        assertEquals("Tour de Denmark", result.raceName)
        assertEquals(150.0f, result.lengthInKm)
        assertEquals(LocalDate.of(2026, 8, 1), result.raceDate)
        assertEquals(Instant.ofEpochSecond(1700000200), result.startTime)
        assertSame(nation, result.nation)
        assertEquals(null, result.cup)
    }
}
