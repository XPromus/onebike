package com.xpromus.onebike_backend.race.mapper

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.mapper.toCupDescriptorDto
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.placement.mapper.toPlacementDescriptorDtoList
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.GetRaceWithChildrenDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto
import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto

fun Race.toGetRaceDto(): GetRaceDto {
    return GetRaceDto(
        id = id!!,
        raceName = raceName,
        lengthInKm = lengthInKm,
        raceDate = raceDate,
        startTime = startTime,
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

fun Race.toGetRaceWithChildrenDto(): GetRaceWithChildrenDto {
    return GetRaceWithChildrenDto(
        id = id!!,
        raceName = raceName,
        lengthInKm = lengthInKm,
        raceDate = raceDate,
        startTime = startTime,
        nation = nation.toNationDescriptorDto(),
        cup = cup?.toCupDescriptorDto(),
        placements = placements.toPlacementDescriptorDtoList(),
    )
}

fun List<Race>.toGetRaceWithChildrenDtoList(): List<GetRaceWithChildrenDto> {
    return map {
        it.toGetRaceWithChildrenDto()
    }
}

fun Race.toRaceDescriptorDto(): RaceDescriptorDto {
    return RaceDescriptorDto(
        id = id!!,
        raceName = raceName,
        lengthInKm = lengthInKm,
        raceDate = raceDate,
        startTime = startTime
    )
}

fun List<Race>.toRaceDescriptorDtoList(): List<RaceDescriptorDto> {
    return map {
        it.toRaceDescriptorDto()
    }
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
        raceDate = raceDate,
        startTime = startTime,
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
        raceDate = raceDate,
        startTime = startTime,
        nation = nation,
        cup = cup,
    )
}
