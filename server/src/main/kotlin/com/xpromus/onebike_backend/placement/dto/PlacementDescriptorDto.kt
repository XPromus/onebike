package com.xpromus.onebike_backend.placement.dto

data class PlacementDescriptorDto(
    val id: Long,
    val points: Int,
    val finishTimeInSeconds: Int,
    val finishStatus: String,
    val raceId: Long,
)
