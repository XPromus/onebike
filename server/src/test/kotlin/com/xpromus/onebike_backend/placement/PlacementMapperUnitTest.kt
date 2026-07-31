package com.xpromus.onebike_backend.placement

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.placement.dto.GetPlacementDto
import com.xpromus.onebike_backend.placement.dto.GetPlacementWithChildrenDto
import com.xpromus.onebike_backend.placement.dto.PlacementDescriptorDto
import com.xpromus.onebike_backend.placement.dto.PostPlacementDto
import com.xpromus.onebike_backend.placement.dto.PutPlacementDto
import com.xpromus.onebike_backend.placement.mapper.toEntity
import com.xpromus.onebike_backend.placement.mapper.toGetPlacementDto
import com.xpromus.onebike_backend.placement.mapper.toGetPlacementWithChildrenDto
import com.xpromus.onebike_backend.placement.mapper.toNewEntity
import com.xpromus.onebike_backend.placement.mapper.toPlacementDescriptorDto
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto
import java.time.Instant
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class PlacementMapperUnitTest {

    private val nation = Nation(
        id = 5L,
        longName = "Germany",
        shortName = "GER",
        flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
    )

    private val rider = Rider(
        id = 2L,
        firstName = "Test",
        lastName = "McTest",
        dateOfBirth = LocalDate.of(2000, 1, 15),
        nation = nation,
        team = null,
    )

    private val race = Race(
        id = 3L,
        raceName = "Lausitz Marathon",
        lengthInKm = 120.0f,
        raceDate = LocalDate.of(2026, 6, 1),
        startTime = Instant.ofEpochSecond(1700000000),
        nation = nation,
    )

    private val placement = Placement(
        id = 1L,
        points = 25,
        finishTimeInSeconds = 3600,
        finishStatus = "finished",
        rider = rider,
        race = race,
    )

    @Test
    fun `toGetPlacementDto maps all fields`() {
        val expected = GetPlacementDto(
            id = 1L,
            points = 25,
            finishTimeInSeconds = 3600,
            finishStatus = "finished",
            riderId = 2L,
            raceId = 3L,
        )

        val actual = placement.toGetPlacementDto(
            riderId = 2L,
            raceId = 3L,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetPlacementWithChildrenDto maps all fields`() {
        val riderDescriptor = RiderDescriptorDto(
            id = 2L,
            firstName = "Test",
            lastName = "McTest",
            dateOfBirth = LocalDate.of(2000, 1, 15),
        )
        val raceDescriptor = RaceDescriptorDto(
            id = 3L,
            raceName = "Lausitz Marathon",
            lengthInKm = 120.0f,
            raceDate = LocalDate.of(2026, 6, 1),
            startTime = Instant.ofEpochSecond(1700000000),
        )

        val expected = GetPlacementWithChildrenDto(
            id = 1L,
            points = 25,
            finishTimeInSeconds = 3600,
            finishStatus = "finished",
            rider = riderDescriptor,
            race = raceDescriptor,
        )

        val actual = placement.toGetPlacementWithChildrenDto(
            rider = riderDescriptor,
            race = raceDescriptor,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toPlacementDescriptorDto maps all fields`() {
        val expected = PlacementDescriptorDto(
            id = 1L,
            points = 25,
            finishTimeInSeconds = 3600,
            finishStatus = "finished",
            raceId = 3L,
        )

        assertEquals(expected, placement.toPlacementDescriptorDto())
    }

    @Test
    fun `PutPlacementDto toEntity preserves id and maps fields`() {
        val put = PutPlacementDto(
            points = 18,
            finishTimeInSeconds = 3650,
            finishStatus = "DNF",
            riderId = 2L,
            raceId = 3L,
        )

        val result = put.toEntity(original = placement, race = race, rider = rider)

        assertEquals(1L, result.id)
        assertEquals(18, result.points)
        assertEquals(3650, result.finishTimeInSeconds)
        assertEquals("DNF", result.finishStatus)
        assertSame(rider, result.rider)
        assertSame(race, result.race)
    }

    @Test
    fun `PutPlacementDto toNewEntity creates entity without id`() {
        val put = PutPlacementDto(
            points = 18,
            finishTimeInSeconds = 3650,
            finishStatus = "DNF",
            riderId = 2L,
            raceId = 3L,
        )

        val result = put.toNewEntity(race = race, rider = rider)

        assertEquals(null, result.id)
        assertEquals(18, result.points)
        assertEquals(3650, result.finishTimeInSeconds)
        assertEquals("DNF", result.finishStatus)
        assertSame(rider, result.rider)
        assertSame(race, result.race)
    }

    @Test
    fun `PostPlacementDto toNewEntity creates entity without id`() {
        val post = PostPlacementDto(
            points = 0,
            finishTimeInSeconds = 0,
            finishStatus = "DNS",
            riderId = 2L,
            raceId = 3L,
        )

        val result = post.toNewEntity(race = race, rider = rider)

        assertEquals(null, result.id)
        assertEquals(0, result.points)
        assertEquals(0, result.finishTimeInSeconds)
        assertEquals("DNS", result.finishStatus)
        assertSame(rider, result.rider)
        assertSame(race, result.race)
    }
}
