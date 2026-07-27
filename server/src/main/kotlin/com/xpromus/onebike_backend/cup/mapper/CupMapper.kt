package com.xpromus.onebike_backend.cup.mapper

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.dto.*
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto

fun Cup.toGetCupDto(
    raceIds: List<Long>,
    nationId: Long,
): GetCupDto {
    return GetCupDto(
        id = id!!,
        cupName = cupName,
        url = url,
        raceIds = raceIds,
        nationId = nationId,
    )
}

fun Cup.toGetCupWithChildrenDto(
    races: List<RaceDescriptorDto>,
    nation: NationDescriptorDto
): GetCupWithChildrenDto {
    return GetCupWithChildrenDto(
        id = id!!,
        cupName = cupName,
        url = url,
        races = races,
        nation = nation,
    )
}

fun Cup.toCupDescriptorDto(): CupDescriptorDto {
    return CupDescriptorDto(
        id = id!!,
        cupName = cupName,
        url = url
    )
}

fun PutCupDto.toEntity(
    originalCup: Cup,
    nation: Nation
): Cup {
    return Cup(
        id = originalCup.id,
        cupName = cupName,
        url = url,
        races = originalCup.races,
        nation = nation
    )
}

fun PutCupDto.toNewEntity(
    nation: Nation
): Cup {
    return Cup(
        cupName = cupName,
        url = url,
        nation = nation,
    )
}

fun PostCupDto.toNewEntity(
    nation: Nation
): Cup {
    return Cup(
        cupName = cupName,
        url = url,
        nation = nation,
    )
}
