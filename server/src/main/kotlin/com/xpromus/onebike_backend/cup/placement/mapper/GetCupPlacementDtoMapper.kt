package com.xpromus.onebike_backend.cup.placement.mapper

import com.xpromus.onebike_backend.cup.placement.CupPlacement
import com.xpromus.onebike_backend.cup.placement.dto.GetCupPlacementDto

fun cupPlacementToGetCupPlacementDto(
    cupPlacement: CupPlacement
): GetCupPlacementDto {
    return GetCupPlacementDto(
        id = cupPlacement.id!!,
        cupId = cupPlacement.id!!,
        points = cupPlacement.points,
        riderId = cupPlacement.rider.id!!
    )
}
