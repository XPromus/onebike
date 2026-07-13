package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupDtoList
import com.xpromus.onebike_backend.nation.Nation
import kotlin.test.Test
import kotlin.test.assertEquals

class CupMapperUnitTests {

    @Test
    fun `toGetCupDto creates correct Dto`() {
        val givenNation: Nation = Nation(
            id = 1L
        )
        val given = Cup(
            id = 1L,
            cupName = "Test Cup",
            races = mutableListOf(),
            nation = givenNation,
            url = ""
        )
        val expected = GetCupDto(
            id = 1L,
            cupName = "Test Cup",
            raceIds = emptyList(),
            cupNationId = 1L,
            url = ""
        )
        val actual = given.toGetCupDto()

        assertEquals(expected, actual)
    }

    @Test
    fun `toGetCupDtoList creates correct Dto List`() {
        val givenNation: Nation = Nation(
            id = 1L
        )
        val given: List<Cup> = listOf(
            Cup(
                id = 1L,
                cupName = "Test Cup",
                races = mutableListOf(),
                nation = givenNation
            ), Cup(
                id = 2L,
                cupName = "Test Cup 2",
                races = mutableListOf(),
                nation = givenNation
            ),
        )
        val expected: List<GetCupDto> = listOf(
            GetCupDto(
                id = 1L,
                cupName = "Test Cup",
                raceIds = mutableListOf(),
                cupNationId = 1L,
                url = ""
            ), GetCupDto(
                id = 2L,
                cupName = "Test Cup 2",
                raceIds = mutableListOf(),
                cupNationId = 1L,
                url = ""
            ),
        )
        val actual = given.toGetCupDtoList()

        assertEquals(expected, actual)
    }

}