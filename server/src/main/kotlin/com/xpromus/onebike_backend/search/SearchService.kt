package com.xpromus.onebike_backend.search

import com.xpromus.onebike_backend.cup.CupRepository
import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupDtoList
import com.xpromus.onebike_backend.race.RaceRepository
import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.mapper.toGetRaceDtoList
import com.xpromus.onebike_backend.rider.RiderRepository
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.mapper.toGetRiderDtoList
import com.xpromus.onebike_backend.search.dto.GetSearchDto
import com.xpromus.onebike_backend.search.dto.PostSearchDto
import com.xpromus.onebike_backend.team.TeamRepository
import com.xpromus.onebike_backend.team.dto.GetTeamDto
import com.xpromus.onebike_backend.team.mapper.toGetTeamDtoList
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val cupRepository: CupRepository,
    private val riderRepository: RiderRepository,
    private val raceRepository: RaceRepository,
    private val teamRepository: TeamRepository
) {

    fun search(
        postSearchDto: PostSearchDto
    ): GetSearchDto {
        val riders: List<GetRiderDto> = riderRepository.findRidersByFirstNameLikeOrLastNameLike(
            firstName = postSearchDto.searchString,
            lastName = postSearchDto.searchString
        ).toGetRiderDtoList()
        val cups: List<GetCupDto> = cupRepository.findCupsByCupNameLike(
            cupName = postSearchDto.searchString
        ).toGetCupDtoList()
        val races: List<GetRaceDto> = raceRepository.findRacesByRaceNameLike(
            raceName = postSearchDto.searchString
        ).toGetRaceDtoList()
        val teams: List<GetTeamDto> = teamRepository.findTeamsByTeamNameLikeOrShortNameLike(
            teamName = postSearchDto.searchString,
            shortName = postSearchDto.searchString
        ).toGetTeamDtoList()

        return GetSearchDto(
            riders = riders,
            cups = cups,
            races = races,
            teams = teams
        )
    }

}
