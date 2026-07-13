package com.xpromus.onebike_backend.search.dto

import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.team.dto.GetTeamDto

data class GetSearchDto(
    val riders: List<GetRiderDto>,
    val cups: List<GetCupWithChildrenDto>,
    val races: List<GetRaceDto>,
    val teams: List<GetTeamDto>,
)
