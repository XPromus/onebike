package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.CupDescriptorDto
import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.dto.PostCupDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.cup.mapper.toCupDescriptorDto
import com.xpromus.onebike_backend.cup.mapper.toEntity
import com.xpromus.onebike_backend.cup.mapper.toGetCupDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.mapper.toNewEntity
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto
import java.time.Instant
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class CupMapperUnitTests {

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

    private val races = mutableListOf(
        Race(
            id = 10L,
            raceName = "Lausitz Marathon",
            lengthInKm = 120.0f,
            raceDate = LocalDate.of(2026, 6, 1),
            startTime = Instant.ofEpochSecond(1700000000),
            nation = nation,
        ),
        Race(
            id = 11L,
            raceName = "Lausitz Sprint",
            lengthInKm = 40.0f,
            raceDate = LocalDate.of(2026, 6, 2),
            startTime = Instant.ofEpochSecond(1700000100),
            nation = nation,
        ),
    )

    private val cup = Cup(
        id = 1L,
        cupName = "Lausitzcup",
        url = "https://www.lausitzcup.de",
        races = races,
        nation = nation,
    )

    @Test
    fun `toGetCupDto maps all fields`() {
        val expected = GetCupDto(
            id = 1L,
            cupName = "Lausitzcup",
            url = "https://www.lausitzcup.de",
            raceIds = listOf(10L, 11L),
            nationId = 5L,
        )

        val actual = cup.toGetCupDto(
            raceIds = listOf(10L, 11L),
            nationId = 5L,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetCupDto handles null url`() {
        val given = Cup(
            id = 2L,
            cupName = "Tour de Germany",
            url = null,
            races = mutableListOf(),
            nation = nation,
        )

        val expected = GetCupDto(
            id = 2L,
            cupName = "Tour de Germany",
            url = null,
            raceIds = emptyList(),
            nationId = 5L,
        )

        val actual = given.toGetCupDto(
            raceIds = emptyList(),
            nationId = 5L,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetCupWithChildrenDto maps all fields`() {
        val raceDescriptors = listOf(
            RaceDescriptorDto(
                id = 10L,
                raceName = "Lausitz Marathon",
                lengthInKm = 120.0f,
                raceDate = LocalDate.of(2026, 6, 1),
                startTime = Instant.ofEpochSecond(1700000000),
            ),
        )
        val nationDescriptor = NationDescriptorDto(
            id = 5L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
        )

        val expected = GetCupWithChildrenDto(
            id = 1L,
            cupName = "Lausitzcup",
            url = "https://www.lausitzcup.de",
            races = raceDescriptors,
            nation = nationDescriptor,
        )

        val actual = cup.toGetCupWithChildrenDto(
            races = raceDescriptors,
            nation = nationDescriptor,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toCupDescriptorDto maps all fields`() {
        val expected = CupDescriptorDto(
            id = 1L,
            cupName = "Lausitzcup",
            url = "https://www.lausitzcup.de",
        )

        assertEquals(expected, cup.toCupDescriptorDto())
    }

    @Test
    fun `toCupDescriptorDto handles null url`() {
        val given = Cup(
            id = 2L,
            cupName = "Tour de Germany",
            url = null,
            races = mutableListOf(),
            nation = nation,
        )

        val expected = CupDescriptorDto(
            id = 2L,
            cupName = "Tour de Germany",
            url = null,
        )

        assertEquals(expected, given.toCupDescriptorDto())
    }

    @Test
    fun `PutCupDto toEntity preserves id and races and maps fields`() {
        val put = PutCupDto(
            cupName = "Updated Cup",
            url = "https://www.example.com",
            nationId = 7L,
        )

        val result = put.toEntity(originalCup = cup, nation = updatedNation)

        assertEquals(1L, result.id)
        assertSame(races, result.races)
        assertEquals("Updated Cup", result.cupName)
        assertEquals("https://www.example.com", result.url)
        assertSame(updatedNation, result.nation)
    }

    @Test
    fun `PutCupDto toNewEntity creates entity without id`() {
        val put = PutCupDto(
            cupName = "Updated Cup",
            url = "https://www.example.com",
            nationId = 7L,
        )

        val result = put.toNewEntity(nation = updatedNation)

        assertEquals(null, result.id)
        assertEquals("Updated Cup", result.cupName)
        assertEquals("https://www.example.com", result.url)
        assertSame(updatedNation, result.nation)
    }

    @Test
    fun `PostCupDto toNewEntity creates entity without id`() {
        val post = PostCupDto(
            cupName = "New Cup",
            url = null,
            nationId = 5L,
        )

        val result = post.toNewEntity(nation = nation)

        assertEquals(null, result.id)
        assertEquals("New Cup", result.cupName)
        assertEquals(null, result.url)
        assertSame(nation, result.nation)
    }
}
