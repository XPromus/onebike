package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.nation.dto.GetNationDto
import com.xpromus.onebike_backend.nation.dto.GetNationWithChildrenDto
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.nation.dto.PutNationDto
import com.xpromus.onebike_backend.nation.mapper.toEntity
import com.xpromus.onebike_backend.nation.mapper.toGetDto
import com.xpromus.onebike_backend.nation.mapper.toGetDtoList
import com.xpromus.onebike_backend.nation.mapper.toGetWithChildrenDto
import com.xpromus.onebike_backend.nation.mapper.toGetWithChildrenDtoList
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.nation.mapper.toNewEntity
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.rider.Rider
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NationMapperUnitTest {

    fun getGivenNation(): Nation {
        return Nation(
            id = 1L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "🇩🇪",
            riders = mutableListOf(
                Rider(id = 1L),
                Rider(id = 2L),
                Rider(id = 3L),
            ),
            cups = mutableListOf(
                Cup(id = 1L),
                Cup(id = 2L),
                Cup(id = 3L),
            ),
            races = mutableListOf(
                Race(id = 1L),
                Race(id = 2L),
                Race(id = 3L),
            )
        )
    }

    fun getGivenNationList(): List<Nation> {
        return listOf(
            getGivenNation(),
            getGivenNation(),
            getGivenNation(),
        )
    }

    fun getExpectedGetNationDto(): GetNationDto {
        return GetNationDto(
            id = 1L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "🇩🇪"
        )
    }

    fun getExpectedGetNationDtoList(): List<GetNationDto> {
        return listOf(
            getExpectedGetNationDto(),
            getExpectedGetNationDto(),
            getExpectedGetNationDto(),
        )
    }

    fun getExpectedGetNationDtoWithChildren(): GetNationWithChildrenDto {
        return GetNationWithChildrenDto(
            id = 1L,
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "🇩🇪",
            riderIds = listOf(1L, 2L, 3L),
            cupIds = listOf(1L, 2L, 3L),
            raceIds = listOf(1L, 2L, 3L)
        )
    }

    fun getExpectedGetNationDtoWithChildrenList(): List<GetNationWithChildrenDto> {
        return listOf(
            getExpectedGetNationDtoWithChildren(),
            getExpectedGetNationDtoWithChildren(),
            getExpectedGetNationDtoWithChildren(),
        )
    }

    fun getExpectedNationDescriptorDto(): NationDescriptorDto {
        return NationDescriptorDto(
            longName = "Germany",
            shortName = "GER",
            flagEmoji = "🇩🇪"
        )
    }

    fun getGivenPutNationDto(): PutNationDto {
        return PutNationDto(
            id = 1L,
            longName = "Spain",
            shortName = "ESP",
            flagEmoji = "🇪🇸",
        )
    }

    fun getExpectedNation(): Nation {
        return Nation(
            id = 1L,
            longName = "Spain",
            shortName = "ESP",
            flagEmoji = "🇪🇸",
        )
    }

    @Test
    fun `toGetDto returns correct GetNationDto`() {
        val nation = getGivenNation()
        val expected = getExpectedGetNationDto()
        val actual = nation.toGetDto()

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetDtoList returns correct list of GetNationDto`() {
        val nationList = getGivenNationList()
        val expected = getExpectedGetNationDtoList()
        val actual = nationList.toGetDtoList()

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetWithChildrenDto returns correct GetNationWithChildrenDto`() {
        val nation = getGivenNation()
        val expected = getExpectedGetNationDtoWithChildren()
        val actual = nation.toGetWithChildrenDto()

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetWithChildrenDtoList returns correct list of GetNationWithChildrenDto`() {
        val nationList = getGivenNationList()
        val expected = getExpectedGetNationDtoWithChildrenList()
        val actual = nationList.toGetWithChildrenDtoList()

        assertEquals(expected, actual)
    }

    @Test
    fun `toNationDescriptorDto returns correct NationDescriptorDto`() {
        val nation = getGivenNation()
        val expected = getExpectedNationDescriptorDto()
        val actual = nation.toNationDescriptorDto()

        assertEquals(expected, actual)
    }

    @Test
    fun `toEntity return the correct nation`() {
        val nation = getGivenNation()
        val putNationDto = getGivenPutNationDto()
        val expected = getExpectedNation()
        val actual = putNationDto.toEntity(
            nation
        )

        assertEquals(expected.id, actual.id)
        assertEquals(expected.longName, actual.longName)
        assertEquals(expected.shortName, actual.shortName)
        assertEquals(expected.flagEmoji, actual.flagEmoji)
    }

    @Test
    fun `toNewEntity returns the correct nation`() {
        val putNationDto = getGivenPutNationDto()
        val expected = getExpectedNation()
        val actual = putNationDto.toNewEntity()

        assertEquals(null, actual.id)
        assertEquals(expected.longName, actual.longName)
        assertEquals(expected.shortName, actual.shortName)
        assertEquals(expected.flagEmoji, actual.flagEmoji)
    }

}
