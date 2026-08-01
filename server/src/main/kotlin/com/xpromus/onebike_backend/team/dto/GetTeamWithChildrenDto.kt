package com.xpromus.onebike_backend.team.dto

import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto
import io.swagger.v3.oas.annotations.media.Schema

data class GetTeamWithChildrenDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val teamName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val shortName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val teamDescription: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val nation: NationDescriptorDto,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val riders: List<RiderDescriptorDto>
)
