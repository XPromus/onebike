package com.xpromus.onebike_backend.cup.placement.mapper

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.placement.CupPlacement
import com.xpromus.onebike_backend.cup.placement.dto.HttpPutCupPlacementDto
import com.xpromus.onebike_backend.rider.Rider

fun httpCupPlacementDtoToCupPlacement(
    httpPutCupPlacementDto: HttpPutCupPlacementDto,
    targetCup: Cup,
    targetRider: Rider
): CupPlacement {
    return CupPlacement(
        cup = targetCup,
        points = httpPutCupPlacementDto.points,
        rider = targetRider
    )
}
