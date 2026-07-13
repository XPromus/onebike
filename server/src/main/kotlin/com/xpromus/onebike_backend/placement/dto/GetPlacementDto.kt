package com.xpromus.onebike_backend.placement.dto

data class GetPlacementDto(
    val id: Long,
    val raceId: Long,
    val finishTimeInSeconds: Int,
    val finishStatus: String,
    val points: Int,
    val riderId: Long
)
