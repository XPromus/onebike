package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.nation.dto.GetNationDto
import com.xpromus.onebike_backend.nation.dto.GetNationWithChildrenDto
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.nation.dto.PostNationDto
import com.xpromus.onebike_backend.nation.dto.PutNationDto
import com.xpromus.onebike_backend.nation.mapper.toEntity
import com.xpromus.onebike_backend.nation.mapper.toGetDto
import com.xpromus.onebike_backend.nation.mapper.toGetWithChildrenDto
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.nation.mapper.toNewEntity
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NationMapperUnitTest {

    private val nation = Nation(
        id = 1L,
        longName = "Germany",
        shortName = "GER",
        flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
    )

    @Test
    fun `toGetDto maps all fields`() {
        val expected = GetNationDto(
            id = 1L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
        )

        assertEquals(expected, nation.toGetDto())
    }

    @Test
    fun `toGetDto throws when id is null`() {
        val given = Nation(
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
        )

        assertFailsWith<IllegalStateException> {
            given.toGetDto()
        }
    }

    @Test
    fun `toGetWithChildrenDto maps all fields`() {
        val expected = GetNationWithChildrenDto(
            id = 1L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
            riderIds = listOf(2L, 3L),
            cupIds = listOf(4L),
            raceIds = listOf(5L, 6L),
        )

        val actual = nation.toGetWithChildrenDto(
            riderIds = listOf(2L, 3L),
            cupIds = listOf(4L),
            raceIds = listOf(5L, 6L),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `toNationDescriptorDto maps all fields`() {
        val expected = NationDescriptorDto(
            id = 1L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
        )

        assertEquals(expected, nation.toNationDescriptorDto())
    }

    @Test
    fun `PutNationDto toEntity preserves id and maps fields`() {
        val put = PutNationDto(
            longName = "Spain",
            shortName = "ESP",
            flagEmoji = "\uD83C\uDDEA\uD83C\uDDF8",
        )

        val result = put.toEntity(original = nation)

        assertEquals(1L, result.id)
        assertEquals("Spain", result.longName)
        assertEquals("ESP", result.shortName)
        assertEquals("\uD83C\uDDEA\uD83C\uDDF8", result.flagEmoji)
    }

    @Test
    fun `PutNationDto toNewEntity creates entity without id`() {
        val put = PutNationDto(
            longName = "Spain",
            shortName = "ESP",
            flagEmoji = "\uD83C\uDDEA\uD83C\uDDF8",
        )

        val result = put.toNewEntity()

        assertEquals(null, result.id)
        assertEquals("Spain", result.longName)
        assertEquals("ESP", result.shortName)
        assertEquals("\uD83C\uDDEA\uD83C\uDDF8", result.flagEmoji)
    }

    @Test
    fun `PostNationDto toNewEntity creates entity without id`() {
        val post = PostNationDto(
            longName = "France",
            shortName = "FRA",
            flagEmoji = "\uD83C\uDDEB\uD83C\uDDF7",
        )

        val result = post.toNewEntity()

        assertEquals(null, result.id)
        assertEquals("France", result.longName)
        assertEquals("FRA", result.shortName)
        assertEquals("\uD83C\uDDEB\uD83C\uDDF7", result.flagEmoji)
    }
}
