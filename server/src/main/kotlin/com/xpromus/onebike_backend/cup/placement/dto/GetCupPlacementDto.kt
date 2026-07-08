package com.xpromus.onebike_backend.cup.placement.dto

data class GetCupPlacementDto(
    val id: Long,
    val cupId: Long,
    val points: Int,
    val riderId: Long
)
