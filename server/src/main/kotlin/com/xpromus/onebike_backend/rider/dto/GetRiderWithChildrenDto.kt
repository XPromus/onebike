package com.xpromus.onebike_backend.rider.dto

import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.team.dto.TeamDescriptorDto

data class GetRiderWithChildrenDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val nation: NationDescriptorDto,
    val team: TeamDescriptorDto?,
)
