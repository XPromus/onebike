package com.xpromus.onebike_backend.race.mapper

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.dto.CupDescriptorDto
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.placement.dto.PlacementDescriptorDto
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.GetRaceWithChildrenDto
import com.xpromus.onebike_backend.race.dto.PostRaceDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto
import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto

fun Race.toGetRaceDto(
    placementIds: List<Long>,
    nationId: Long,
    cupId: Long?,
): GetRaceDto {
    return GetRaceDto(
        id = id!!,
        raceName = raceName,
        lengthInKm = lengthInKm,
        raceDate = raceDate,
        startTime = startTime,
        nationId = nationId,
        cupId = cupId,
        placementIds = placementIds
    )
}

fun Race.toGetRaceWithChildrenDto(
    placements: List<PlacementDescriptorDto>,
    nation: NationDescriptorDto,
    cup: CupDescriptorDto?,
): GetRaceWithChildrenDto {
    return GetRaceWithChildrenDto(
        id = id!!,
        raceName = raceName,
        lengthInKm = lengthInKm,
        raceDate = raceDate,
        startTime = startTime,
        nation = nation,
        cup = cup,
        placements = placements,
    )
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

fun PutRaceDto.toEntity(
    original: Race,
    nation: Nation,
    cup: Cup?,
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
    cup: Cup?,
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

fun PostRaceDto.toNewEntity(
    nation: Nation,
    cup: Cup?,
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
