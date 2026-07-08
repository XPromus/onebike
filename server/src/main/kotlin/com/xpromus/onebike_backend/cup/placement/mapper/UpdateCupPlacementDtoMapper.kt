package com.xpromus.onebike_backend.cup.placement.mapper

import com.xpromus.onebike_backend.cup.placement.CupPlacement
import com.xpromus.onebike_backend.cup.placement.dto.UpdateCupPlacementDto

fun updateCupPlacementDtoToCupPlacement(
    updateCupPlacementDto: UpdateCupPlacementDto
): CupPlacement {
    return CupPlacement(
        id = updateCupPlacementDto.id,
        cup = updateCupPlacementDto.targetCup,
        points = updateCupPlacementDto.points,
        rider = updateCupPlacementDto.targetRider
    )
}
