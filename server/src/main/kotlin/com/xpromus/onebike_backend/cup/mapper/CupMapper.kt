package com.xpromus.onebike_backend.cup.mapper

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.nation.Nation

fun Cup.toGetCupDto(): GetCupDto {
    return GetCupDto(
        id = id!!,
        cupName = cupName,
        raceIds = races.map {
            it.id!!
        },
        cupNationId = cupNation.id!!
    )
}

fun List<Cup>.toGetCupDtoList(): List<GetCupDto> {
    return map {
        it.toGetCupDto()
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
        cupNation = nation
    )
}

fun PutCupDto.toNewEntity(
    nation: Nation
): Cup {
    return Cup(
        cupName = cupName,
        cupNation = nation
    )
}
