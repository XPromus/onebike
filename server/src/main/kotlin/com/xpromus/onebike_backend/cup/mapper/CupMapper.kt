package com.xpromus.onebike_backend.cup.mapper

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.dto.CupDescriptorDto
import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.race.mapper.toRaceDescriptorDtoList

fun Cup.toGetCupDto(): GetCupDto {
    return GetCupDto(
        id = id!!,
        cupName = cupName,
        url = url,
        raceIds = races.map { it.id!! },
        nationId = nation.id!!,
    )
}

fun List<Cup>.toGetCupDtoList(): List<GetCupDto> {
    return map {
        it.toGetCupDto()
    }
}

fun Cup.toGetCupWithChildrenDto(): GetCupWithChildrenDto {
    return GetCupWithChildrenDto(
        id = id!!,
        cupName = cupName,
        url = url,
        races = races.toRaceDescriptorDtoList(),
        nation = nation.toNationDescriptorDto(),
    )
}

fun List<Cup>.toGetCupWithChildrenDtoList(): List<GetCupWithChildrenDto> {
    return map {
        it.toGetCupWithChildrenDto()
    }
}

fun Cup.toCupDescriptorDto(): CupDescriptorDto {
    return CupDescriptorDto(
        id = id!!,
        cupName = cupName,
        url = url
    )
}

fun List<Cup>.toCupDescriptorDtoList(): List<CupDescriptorDto> {
    return map {
        it.toCupDescriptorDto()
    }
}

fun PutCupDto.toEntity(
    originalCup: Cup,
    nation: Nation
): Cup {
    return Cup(
        id = originalCup.id,
        cupName = cupName,
        races = originalCup.races,
        nation = nation
    )
}

fun PutCupDto.toNewEntity(
    nation: Nation
): Cup {
    return Cup(
        cupName = cupName,
        nation = nation
    )
}
