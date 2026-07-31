package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.placement.Placement
import com.xpromus.onebike_backend.placement.dto.PlacementDescriptorDto
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.dto.PostRiderDto
import com.xpromus.onebike_backend.rider.dto.PutRiderDto
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto
import com.xpromus.onebike_backend.rider.mapper.toEntity
import com.xpromus.onebike_backend.rider.mapper.toGetRiderDto
import com.xpromus.onebike_backend.rider.mapper.toGetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.mapper.toNewEntity
import com.xpromus.onebike_backend.rider.mapper.toRiderDescriptorDto
import com.xpromus.onebike_backend.team.Team
import com.xpromus.onebike_backend.team.dto.TeamDescriptorDto
import java.time.Instant
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class RiderMapperUnitTest {

    private val nation = Nation(
        id = 5L,
        longName = "Germany",
        shortName = "GER",
        flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
    )

    private val team = Team(
        id = 6L,
        teamName = "Team 1",
        shortName = "T1",
        teamDescription = "Test Team 1",
        nation = nation,
    )

    private val rider = Rider(
        id = 1L,
        firstName = "Test",
        lastName = "McTest",
        dateOfBirth = LocalDate.of(2000, 1, 15),
        nation = nation,
        team = team,
    )

    private val race = Race(
        id = 9L,
        raceName = "Lausitz Marathon",
        lengthInKm = 120.0f,
        raceDate = LocalDate.of(2026, 6, 1),
        startTime = Instant.ofEpochSecond(1700000000),
        nation = nation,
    )

    private val placements: MutableList<Placement> = mutableListOf()

    init {
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
        rider.placements = placements
    }

    @Test
    fun `toGetRiderDto maps all fields with teamId`() {
        val expected = GetRiderDto(
            id = 1L,
            firstName = "Test",
            lastName = "McTest",
            dateOfBirth = LocalDate.of(2000, 1, 15),
            placementIds = listOf(8L),
            nationId = 5L,
            teamId = 6L,
        )

        val actual = rider.toGetRiderDto(
            placementIds = listOf(8L),
            nationId = 5L,
            teamId = 6L,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetRiderDto maps teamId as null`() {
        val expected = GetRiderDto(
            id = 1L,
            firstName = "Test",
            lastName = "McTest",
            dateOfBirth = LocalDate.of(2000, 1, 15),
            placementIds = emptyList(),
            nationId = 5L,
            teamId = null,
        )

        val actual = rider.toGetRiderDto(
            placementIds = emptyList(),
            nationId = 5L,
            teamId = null,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetRiderWithChildrenDto maps all fields`() {
        val placementDescriptors = listOf(
            PlacementDescriptorDto(
                id = 8L,
                points = 25,
                finishTimeInSeconds = 3600,
                finishStatus = "finished",
                raceId = 9L,
            ),
        )
        val nationDescriptor = NationDescriptorDto(
            id = 5L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
        )
        val teamDescriptor = TeamDescriptorDto(
            id = 6L,
            teamName = "Team 1",
            shortName = "T1",
            teamDescription = "Test Team 1",
        )

        val expected = GetRiderWithChildrenDto(
            id = 1L,
            firstName = "Test",
            lastName = "McTest",
            dateOfBirth = LocalDate.of(2000, 1, 15),
            placements = placementDescriptors,
            nation = nationDescriptor,
            team = teamDescriptor,
        )

        val actual = rider.toGetRiderWithChildrenDto(
            placements = placementDescriptors,
            nation = nationDescriptor,
            team = teamDescriptor,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetRiderWithChildrenDto maps team as null`() {
        val nationDescriptor = NationDescriptorDto(
            id = 5L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
        )

        val expected = GetRiderWithChildrenDto(
            id = 1L,
            firstName = "Test",
            lastName = "McTest",
            dateOfBirth = LocalDate.of(2000, 1, 15),
            placements = emptyList(),
            nation = nationDescriptor,
            team = null,
        )

        val actual = rider.toGetRiderWithChildrenDto(
            placements = emptyList(),
            nation = nationDescriptor,
            team = null,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toRiderDescriptorDto maps all fields`() {
        val expected = RiderDescriptorDto(
            id = 1L,
            firstName = "Test",
            lastName = "McTest",
            dateOfBirth = LocalDate.of(2000, 1, 15),
        )

        assertEquals(expected, rider.toRiderDescriptorDto())
    }

    @Test
    fun `PutRiderDto toEntity preserves id and placements and maps fields`() {
        val put = PutRiderDto(
            firstName = "Jupiter",
            lastName = "Rider",
            dateOfBirth = LocalDate.of(2002, 6, 10),
            nationId = 5L,
            teamId = null,
        )

        val result = put.toEntity(original = rider, nation = nation, team = null)

        assertEquals(1L, result.id)
        assertSame(placements, result.placements)
        assertEquals("Jupiter", result.firstName)
        assertEquals("Rider", result.lastName)
        assertEquals(LocalDate.of(2002, 6, 10), result.dateOfBirth)
        assertSame(nation, result.nation)
        assertEquals(null, result.team)
    }

    @Test
    fun `PutRiderDto toNewEntity creates entity without id`() {
        val put = PutRiderDto(
            firstName = "Jupiter",
            lastName = "Rider",
            dateOfBirth = LocalDate.of(2002, 6, 10),
            nationId = 5L,
            teamId = 6L,
        )

        val result = put.toNewEntity(nation = nation, team = team)

        assertEquals(null, result.id)
        assertEquals("Jupiter", result.firstName)
        assertEquals("Rider", result.lastName)
        assertEquals(LocalDate.of(2002, 6, 10), result.dateOfBirth)
        assertSame(nation, result.nation)
        assertSame(team, result.team)
    }

    @Test
    fun `PostRiderDto toNewEntity creates entity without id`() {
        val post = PostRiderDto(
            firstName = "Kotlin",
            lastName = "Unit",
            dateOfBirth = LocalDate.of(1998, 12, 5),
            nationId = 5L,
            teamId = null,
        )

        val result = post.toNewEntity(nation = nation, team = null)

        assertEquals(null, result.id)
        assertEquals("Kotlin", result.firstName)
        assertEquals("Unit", result.lastName)
        assertEquals(LocalDate.of(1998, 12, 5), result.dateOfBirth)
        assertSame(nation, result.nation)
        assertEquals(null, result.team)
    }
}
