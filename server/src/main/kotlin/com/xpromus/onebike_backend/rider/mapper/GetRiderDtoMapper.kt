package com.xpromus.onebike_backend.rider.mapper

import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.GetRiderDto

fun riderToGetRiderDto(
    rider: Rider
): GetRiderDto {
    return GetRiderDto(
        id = rider.id!!,
        firstName = rider.firstName,
        lastName = rider.lastName,
        cupPlacementIDs = rider.cupPlacements.map {
            it.id!!
        },
        nationId = rider.nation.id!!
    )
}
