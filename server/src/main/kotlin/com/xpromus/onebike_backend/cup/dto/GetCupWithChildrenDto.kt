package com.xpromus.onebike_backend.cup.dto

import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto
import io.swagger.v3.oas.annotations.media.Schema

data class GetCupWithChildrenDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val cupName: String,
    val url: String?,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val races: List<RaceDescriptorDto>,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val nation: NationDescriptorDto
)
