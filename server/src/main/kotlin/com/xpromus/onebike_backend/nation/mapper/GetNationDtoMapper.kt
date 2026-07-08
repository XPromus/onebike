package com.xpromus.onebike_backend.nation.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.GetNationDto

fun nationToGetNationDtoMapper(
    nation: Nation
): GetNationDto {
    return GetNationDto(
        id = nation.id!!,
        longName = nation.longName,
        shortName = nation.shortName,
        flagEmoji = nation.flagEmoji
    )
}
