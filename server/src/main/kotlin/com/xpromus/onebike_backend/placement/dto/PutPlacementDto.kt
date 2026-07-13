package com.xpromus.onebike_backend.placement.dto

data class PutPlacementDto(
    val id: Long?,
    val targetRaceId: Long,
    val points: Int,
    val targetRiderId: Long
)
