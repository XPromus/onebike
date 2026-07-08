package com.xpromus.onebike_backend.nation.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.CreateNationDto

fun createNationDtoToNation(
    createNationDto: CreateNationDto
): Nation {
    return Nation(
        longName = createNationDto.longName,
        shortName = createNationDto.shortName,
        flagEmoji = createNationDto.flagEmoji
    )
}
