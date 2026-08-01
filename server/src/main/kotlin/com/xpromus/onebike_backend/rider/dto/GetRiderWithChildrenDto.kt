package com.xpromus.onebike_backend.rider.dto

import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.placement.dto.PlacementDescriptorDto
import com.xpromus.onebike_backend.team.dto.TeamDescriptorDto
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class GetRiderWithChildrenDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val firstName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val lastName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val dateOfBirth: LocalDate,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val placements: List<PlacementDescriptorDto>,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val nation: NationDescriptorDto,
    val team: TeamDescriptorDto?,
)
