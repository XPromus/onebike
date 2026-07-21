package com.xpromus.onebike_backend.nation.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.GetNationDto
import com.xpromus.onebike_backend.nation.dto.GetNationWithChildrenDto
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.nation.dto.PutNationDto

fun Nation.toGetDto(): GetNationDto {
    return GetNationDto(
        id = id ?: throw IllegalStateException("Nation must have ID"),
        longName = longName,
        shortName = shortName,
        flagEmoji = flagEmoji,
    )
}

fun List<Nation>.toGetDtoList(): List<GetNationDto> {
    return map {
        it.toGetDto()
    }
}

fun Nation.toGetWithChildrenDto(): GetNationWithChildrenDto {
    return GetNationWithChildrenDto(
        id = id ?: throw IllegalStateException("Nation must have ID"),
        longName = longName,
        shortName = shortName,
        flagEmoji = flagEmoji,
        riderIds = riders.map { it.id!! },
        cupIds = cups.map { it.id!! },
        raceIds = races.map { it.id!! },
    )
}

fun List<Nation>.toGetWithChildrenDtoList(): List<GetNationWithChildrenDto> = map {
    it.toGetWithChildrenDto()
}

fun Nation.toNationDescriptorDto(): NationDescriptorDto {
    return NationDescriptorDto(
        id = id!!,
        longName = longName,
        shortName = shortName,
        flagEmoji = flagEmoji
    )
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
