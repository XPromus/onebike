package com.xpromus.onebike_backend.cup.placement.mapper

import com.xpromus.onebike_backend.cup.placement.CupPlacement
import com.xpromus.onebike_backend.cup.placement.dto.CreateCupPlacementDto

fun createCupPlacementDtoToCupPlacement(
    createCupPlacementDto: CreateCupPlacementDto
): CupPlacement {
    return CupPlacement(
        cup = createCupPlacementDto.targetCup,
        points = createCupPlacementDto.points,
        rider = createCupPlacementDto.targetRider
    )
}
