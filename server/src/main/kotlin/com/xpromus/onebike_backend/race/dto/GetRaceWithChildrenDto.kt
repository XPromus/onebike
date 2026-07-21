package com.xpromus.onebike_backend.race.dto

import com.xpromus.onebike_backend.cup.dto.CupDescriptorDto
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.placement.dto.PlacementDescriptorDto
import java.time.Instant
import java.time.LocalDate

data class GetRaceWithChildrenDto(
    val id: Long,
    val raceName: String,
    val lengthInKm: Float,
    val raceDate: LocalDate,
    val startTime: Instant,
    val nation: NationDescriptorDto,
    val cup: CupDescriptorDto?,
    val placements: List<PlacementDescriptorDto>,
)
