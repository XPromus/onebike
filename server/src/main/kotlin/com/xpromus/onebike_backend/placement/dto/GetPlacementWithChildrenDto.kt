package com.xpromus.onebike_backend.placement.dto

import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto
import io.swagger.v3.oas.annotations.media.Schema

data class GetPlacementWithChildrenDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val points: Int,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val finishTimeInSeconds: Int,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val finishStatus: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val rider: RiderDescriptorDto,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val race: RaceDescriptorDto,
)
