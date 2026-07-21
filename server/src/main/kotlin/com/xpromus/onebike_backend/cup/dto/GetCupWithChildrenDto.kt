package com.xpromus.onebike_backend.cup.dto

import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto

data class GetCupWithChildrenDto(
    val id: Long,
    val cupName: String,
    val url: String?,
    val races: List<RaceDescriptorDto>,
    val nation: NationDescriptorDto
)
