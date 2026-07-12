package com.xpromus.onebike_backend.cup.placement.dto

data class GetPlacementDto(
    val id: Long,
    val raceId: Long,
    val points: Int,
    val riderId: Long
)
