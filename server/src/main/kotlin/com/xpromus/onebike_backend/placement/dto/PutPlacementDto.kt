package com.xpromus.onebike_backend.placement.dto

data class PutPlacementDto(
    val id: Long?,
    val points: Int,
    val finishTimeInSeconds: Int,
    val finishStatus: String,
    val riderId: Long,
    val raceId: Long,
)
