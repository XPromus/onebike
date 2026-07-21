package com.xpromus.onebike_backend.placement.dto

import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto

data class GetPlacementWithChildrenDto(
    val id: Long,
    val points: Int,
    val finishTimeInSeconds: Int,
    val finishStatus: String,
    val rider: RiderDescriptorDto,
    val race: RaceDescriptorDto,
)
