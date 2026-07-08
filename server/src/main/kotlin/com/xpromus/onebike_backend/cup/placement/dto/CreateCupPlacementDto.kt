package com.xpromus.onebike_backend.cup.placement.dto

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.rider.Rider

data class CreateCupPlacementDto(
    val targetCup: Cup,
    val points: Int,
    val targetRider: Rider
)
