package com.xpromus.onebike_backend.race.dto

import com.xpromus.onebike_backend.cup.dto.CupDescriptorDto
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.placement.dto.PlacementDescriptorDto
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant
import java.time.LocalDate

data class GetRaceWithChildrenDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val raceName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val lengthInKm: Float,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val raceDate: LocalDate,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val startTime: Instant,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val nation: NationDescriptorDto,
    val cup: CupDescriptorDto?,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val placements: List<PlacementDescriptorDto>,
)
