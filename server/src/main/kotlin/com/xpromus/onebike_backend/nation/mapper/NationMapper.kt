package com.xpromus.onebike_backend.nation.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.GetNationDto
import com.xpromus.onebike_backend.nation.dto.PutNationDto

fun Nation.toGetDto(): GetNationDto {
    return GetNationDto(
        id = id ?: throw IllegalStateException("Nation must have ID"),
        longName = longName,
        shortName = shortName,
        flagEmoji = flagEmoji,
    )
}

fun List<Nation>.toGetDtoList(): List<GetNationDto> = map {
    it.toGetDto()
}

fun PutNationDto.toEntity(
    original: Nation
): Nation {
    return Nation(
        id = original.id,
        longName = longName,
        shortName = shortName,
        flagEmoji = flagEmoji
    )
}

fun PutNationDto.toNewEntity(): Nation {
    return Nation(
        longName = longName,
        shortName = shortName,
        flagEmoji = flagEmoji
    )
}
