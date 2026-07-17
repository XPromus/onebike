package com.xpromus.onebike_backend.rider.dto

import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.team.dto.TeamDescriptorDto
import java.time.LocalDate

data class GetRiderWithChildrenDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val nation: NationDescriptorDto,
    val team: TeamDescriptorDto?,
)
