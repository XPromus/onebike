package com.xpromus.onebike_backend.cup.placement.mapper

import com.xpromus.onebike_backend.cup.placement.Placement
import com.xpromus.onebike_backend.cup.placement.dto.GetPlacementDto
import com.xpromus.onebike_backend.cup.placement.dto.PutPlacementDto
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.rider.Rider

fun Placement.toGetPlacementDto(): GetPlacementDto {
    return GetPlacementDto(
        id = id!!,
        raceId = race.id!!,
        points = points,
        riderId = rider.id!!
    )
}

fun List<Placement>.toGetPlacementDtoList(): List<GetPlacementDto> {
    return map {
        it.toGetPlacementDto()
    }
}

fun PutPlacementDto.toEntity(
    original: Placement,
    race: Race,
    rider: Rider
): Placement {
    return Placement(
        id = original.id,
        race = race,
        rider = rider,
        points = points
    )
}

fun PutPlacementDto.toNewEntity(
    race: Race,
    rider: Rider
): Placement {
    return Placement(
        race = race,
        rider = rider,
        points = points
    )
}
