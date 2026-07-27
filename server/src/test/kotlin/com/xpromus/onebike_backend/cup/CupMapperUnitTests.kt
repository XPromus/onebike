package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupWithChildrenDto
import com.xpromus.onebike_backend.nation.Nation
import kotlin.test.Test
import kotlin.test.assertEquals

class CupMapperUnitTests {

//    @Test
//    fun `toGetCupDto creates correct Dto`() {
//        val givenNation: Nation = Nation(
//            id = 1L
//        )
//        val given = Cup(
//            id = 1L,
//            cupName = "Test Cup",
//            races = mutableListOf(),
//            nation = givenNation,
//            url = ""
//        )
//        val expected = GetCupWithChildrenDto(
//            id = 1L,
//            cupName = "Test Cup",
//            raceIds = emptyList(),
//            cupNationId = 1L,
//            url = ""
//        )
//        val actual = given.toGetCupWithChildrenDto()
//
//        assertEquals(expected, actual)
//    }

//    @Test
//    fun `toGetCupDtoList creates correct Dto List`() {
//        val givenNation: Nation = Nation(
//            id = 1L
//        )
//        val given: List<Cup> = listOf(
//            Cup(
//                id = 1L,
//                cupName = "Test Cup",
//                races = mutableListOf(),
//                nation = givenNation
//            ), Cup(
//                id = 2L,
//                cupName = "Test Cup 2",
//                races = mutableListOf(),
//                nation = givenNation
//            ),
//        )
//        val expected: List<GetCupWithChildrenDto> = listOf(
//            GetCupWithChildrenDto(
//                id = 1L,
//                cupName = "Test Cup",
//                raceIds = mutableListOf(),
//                cupNationId = 1L,
//                url = ""
//            ), GetCupWithChildrenDto(
//                id = 2L,
//                cupName = "Test Cup 2",
//                raceIds = mutableListOf(),
//                cupNationId = 1L,
//                url = ""
//            ),
//        )
//        val actual = given.toGetCupWithChildrenDtoList()
//
//        assertEquals(expected, actual)
//    }

}