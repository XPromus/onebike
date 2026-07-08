package com.xpromus.onebike_backend.rider.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.CreateRiderDto

fun createRiderDtoToRider(
    createRiderDto: CreateRiderDto,
    targetNation: Nation
): Rider {
    return Rider(
        firstName = createRiderDto.firstName,
        lastName = createRiderDto.lastName,
        nation = targetNation
    )
}
