package com.xpromus.onebike_backend.rider.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.UpdateRiderDto

fun updateRiderDtoToRider(
    updateRiderDto: UpdateRiderDto,
    nation: Nation
): Rider {
    return Rider(
        id = updateRiderDto.id,
        firstName = updateRiderDto.firstName,
        lastName = updateRiderDto.lastName,
        nation = nation
    )
}
