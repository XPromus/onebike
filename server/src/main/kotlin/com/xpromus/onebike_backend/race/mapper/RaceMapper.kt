package com.xpromus.onebike_backend.race.mapper

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto

fun Race.toGetRaceDto(): GetRaceDto {
    return GetRaceDto(
        id = id!!,
        raceName = raceName,
        lengthInKm = lengthInKm,
        countryId = nation.id!!,
        cupId = cup?.let { it.id!! },
        placementIds = placements.map {
            it.id!!
        }
    )
}

fun List<Race>.toGetRaceDtoList(): List<GetRaceDto> = map {
    it.toGetRaceDto()
}

fun PutRaceDto.toEntity(
    original: Race,
    nation: Nation,
    cup: Cup?
): Race {
    return Race(
        id = original.id,
        raceName = raceName,
        lengthInKm = lengthInKm,
        nation = nation,
        cup = cup,
        placements = original.placements
    )
}

fun PutRaceDto.toNewEntity(
    nation: Nation,
    cup: Cup?
): Race {
    return Race(
        raceName = raceName,
        lengthInKm = lengthInKm,
        nation = nation,
        cup = cup,
    )
}
