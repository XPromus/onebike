package com.xpromus.onebike_backend.team.dto

import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto

data class GetTeamWithChildrenDto(
    val id: Long,
    val teamName: String,
    val shortName: String,
    val teamDescription: String,
    val nation: NationDescriptorDto,
    val riders: List<RiderDescriptorDto>
)
