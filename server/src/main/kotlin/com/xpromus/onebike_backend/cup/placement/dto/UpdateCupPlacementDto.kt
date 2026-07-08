package com.xpromus.onebike_backend.cup.placement.dto

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.rider.Rider

data class UpdateCupPlacementDto(
    val id: Long,
    val targetCup: Cup,
    val points: Int,
    val targetRider: Rider
)
