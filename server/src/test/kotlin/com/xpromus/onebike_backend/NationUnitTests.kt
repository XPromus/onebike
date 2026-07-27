package com.xpromus.onebike_backend

import com.xpromus.onebike_backend.cup.CupRepository
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.nation.NationService
import com.xpromus.onebike_backend.race.RaceRepository
import com.xpromus.onebike_backend.rider.RiderRepository
import io.mockk.mockk
import kotlin.test.Test

class NationUnitTests {

    private val nationRepository: NationRepository = mockk<NationRepository>()
    private val riderRepository: RiderRepository = mockk<RiderRepository>()
    private val cupRepository: CupRepository = mockk<CupRepository>()
    private val raceRepository: RaceRepository = mockk<RaceRepository>()
    private val nationService: NationService = NationService(
        nationRepository,
        riderRepository,
        cupRepository,
        raceRepository,
    )

    @Test
    fun `creates nation and returns GetNationDto`() {

        TODO()

//        val createNationDto = CreateNationDto(
//            longName = "Germany",
//            shortName = "GER",
//            flagEmoji = "🇩🇪"
//        )
//        val savedNation = Nation(
//            id = 1L,
//            longName = "Germany",
//            shortName = "GER",
//            flagEmoji = "🇩🇪"
//        )
//        val getNationDto = GetNationDto(
//            id = 1L,
//            longName = "Germany",
//            shortName = "GER",
//            flagEmoji = "🇩🇪"
//        )
//
//        every { nationRepository.save(any()) } returns savedNation
//
//        val result = nationService.createNation(createNationDto)
//
//        assertEquals(getNationDto, result)
//        verify(exactly = 1) { nationRepository.save(any()) }
    }

}
