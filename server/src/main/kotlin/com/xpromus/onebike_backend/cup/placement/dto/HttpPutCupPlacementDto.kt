package com.xpromus.onebike_backend.cup.placement.dto

data class HttpPutCupPlacementDto(
    val id: Long,
    val targetCupId: Long,
    val points: Int,
    val targetRiderId: Long
)
