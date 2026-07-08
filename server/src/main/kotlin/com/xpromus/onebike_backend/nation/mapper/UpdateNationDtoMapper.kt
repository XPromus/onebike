package com.xpromus.onebike_backend.nation.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.UpdateNationDto

fun updateNationDtoToNation(
    updateNationDto: UpdateNationDto
): Nation {
    return Nation(
        id = updateNationDto.id,
        longName = updateNationDto.longName,
        shortName = updateNationDto.shortName,
        flagEmoji = updateNationDto.flagEmoji
    )
}
