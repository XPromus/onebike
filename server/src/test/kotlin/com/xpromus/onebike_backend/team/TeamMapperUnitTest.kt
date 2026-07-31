package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto
import com.xpromus.onebike_backend.team.dto.GetTeamDto
import com.xpromus.onebike_backend.team.dto.GetTeamWithChildrenDto
import com.xpromus.onebike_backend.team.dto.PostTeamDto
import com.xpromus.onebike_backend.team.dto.PutTeamDto
import com.xpromus.onebike_backend.team.dto.TeamDescriptorDto
import com.xpromus.onebike_backend.team.mapper.toEntity
import com.xpromus.onebike_backend.team.mapper.toGetTeamDto
import com.xpromus.onebike_backend.team.mapper.toGetTeamWithChildrenDto
import com.xpromus.onebike_backend.team.mapper.toNewEntity
import com.xpromus.onebike_backend.team.mapper.toTeamDescriptorDto
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class TeamMapperUnitTest {

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

    private val riders = mutableListOf(
        Rider(
            id = 2L,
            firstName = "Test",
            lastName = "McTest",
            dateOfBirth = LocalDate.of(2000, 1, 15),
            nation = nation,
            team = null,
        ),
        Rider(
            id = 3L,
            firstName = "Jupiter",
            lastName = "Rider",
            dateOfBirth = LocalDate.of(2002, 6, 10),
            nation = nation,
            team = null,
        ),
    )

    private val team = Team(
        id = 1L,
        teamName = "Post SV Görlitz",
        shortName = "PSG",
        teamDescription = "Team from Görlitz",
        nation = nation,
        riders = riders,
    )

    @Test
    fun `toGetTeamDto maps all fields`() {
        val expected = GetTeamDto(
            id = 1L,
            teamName = "Post SV Görlitz",
            shortName = "PSG",
            teamDescription = "Team from Görlitz",
            nationId = 5L,
            riderIds = listOf(2L, 3L),
        )

        val actual = team.toGetTeamDto(
            nationId = 5L,
            riderIds = listOf(2L, 3L),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetTeamWithChildrenDto maps all fields`() {
        val riderDescriptors = listOf(
            RiderDescriptorDto(
                id = 2L,
                firstName = "Test",
                lastName = "McTest",
                dateOfBirth = LocalDate.of(2000, 1, 15),
            ),
        )
        val nationDescriptor = NationDescriptorDto(
            id = 5L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
        )

        val expected = GetTeamWithChildrenDto(
            id = 1L,
            teamName = "Post SV Görlitz",
            shortName = "PSG",
            teamDescription = "Team from Görlitz",
            nation = nationDescriptor,
            riders = riderDescriptors,
        )

        val actual = team.toGetTeamWithChildrenDto(
            nation = nationDescriptor,
            riders = riderDescriptors,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toTeamDescriptorDto maps all fields`() {
        val expected = TeamDescriptorDto(
            id = 1L,
            teamName = "Post SV Görlitz",
            shortName = "PSG",
            teamDescription = "Team from Görlitz",
        )

        assertEquals(expected, team.toTeamDescriptorDto())
    }

    @Test
    fun `PutTeamDto toEntity preserves id and riders and maps fields`() {
        val put = PutTeamDto(
            teamName = "Team Deutschland",
            shortName = "TD",
            teamDescription = "Team from Germany",
            nationId = 7L,
        )

        val result = put.toEntity(original = team, nation = updatedNation)

        assertEquals(1L, result.id)
        assertSame(riders, result.riders)
        assertEquals("Team Deutschland", result.teamName)
        assertEquals("TD", result.shortName)
        assertEquals("Team from Germany", result.teamDescription)
        assertSame(updatedNation, result.nation)
    }

    @Test
    fun `PutTeamDto toNewEntity creates entity without id`() {
        val put = PutTeamDto(
            teamName = "Team Deutschland",
            shortName = "TD",
            teamDescription = "Team from Germany",
            nationId = 7L,
        )

        val result = put.toNewEntity(nation = updatedNation)

        assertEquals(null, result.id)
        assertEquals("Team Deutschland", result.teamName)
        assertEquals("TD", result.shortName)
        assertEquals("Team from Germany", result.teamDescription)
        assertSame(updatedNation, result.nation)
    }

    @Test
    fun `PostTeamDto toNewEntity creates entity without id`() {
        val post = PostTeamDto(
            teamName = "New Team",
            shortName = "NT",
            teamDescription = "Brand new team",
            nationId = 5L,
        )

        val result = post.toNewEntity(nation = nation)

        assertEquals(null, result.id)
        assertEquals("New Team", result.teamName)
        assertEquals("NT", result.shortName)
        assertEquals("Brand new team", result.teamDescription)
        assertSame(nation, result.nation)
    }
}
