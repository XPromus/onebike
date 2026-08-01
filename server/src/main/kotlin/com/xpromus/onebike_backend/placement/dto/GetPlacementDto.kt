package com.xpromus.onebike_backend.placement.dto

import io.swagger.v3.oas.annotations.media.Schema

data class GetPlacementDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val points: Int,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val finishTimeInSeconds: Int,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val finishStatus: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val riderId: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val raceId: Long,
)
