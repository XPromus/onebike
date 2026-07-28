package com.xpromus.onebike_backend.placement.mapper

import com.xpromus.onebike_backend.placement.Placement
import com.xpromus.onebike_backend.placement.dto.GetPlacementDto
import com.xpromus.onebike_backend.placement.dto.GetPlacementWithChildrenDto
import com.xpromus.onebike_backend.placement.dto.PlacementDescriptorDto
import com.xpromus.onebike_backend.placement.dto.PostPlacementDto
import com.xpromus.onebike_backend.placement.dto.PutPlacementDto
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.dto.RaceDescriptorDto
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto

fun Placement.toGetPlacementDto(
    riderId: Long,
    raceId: Long,
): GetPlacementDto {
    return GetPlacementDto(
        id = id!!,
        raceId = raceId,
        finishTimeInSeconds = finishTimeInSeconds,
        finishStatus = finishStatus,
        points = points,
        riderId = riderId
    )
}

fun Placement.toGetPlacementWithChildrenDto(
    rider: RiderDescriptorDto,
    race: RaceDescriptorDto,
): GetPlacementWithChildrenDto {
    return GetPlacementWithChildrenDto(
        id = id!!,
        race = race,
        finishTimeInSeconds = finishTimeInSeconds,
        finishStatus = finishStatus,
        points = points,
        rider = rider
    )
}

fun Placement.toPlacementDescriptorDto(): PlacementDescriptorDto {
    return PlacementDescriptorDto(
        id = id!!,
        finishTimeInSeconds = finishTimeInSeconds,
        finishStatus = finishStatus,
        points = points,
        raceId = race.id!!
    )
}

fun PutPlacementDto.toEntity(
    original: Placement,
    race: Race,
    rider: Rider,
): Placement {
    return Placement(
        id = original.id,
        points = points,
        finishTimeInSeconds = finishTimeInSeconds,
        finishStatus = finishStatus,
        race = race,
        rider = rider,
    )
}

fun PutPlacementDto.toNewEntity(
    race: Race,
    rider: Rider,
): Placement {
    return Placement(
        points = points,
        finishTimeInSeconds = finishTimeInSeconds,
        finishStatus = finishStatus,
        race = race,
        rider = rider,
    )
}

fun PostPlacementDto.toNewEntity(
    race: Race,
    rider: Rider,
): Placement {
    return Placement(
        points = points,
        finishTimeInSeconds = finishTimeInSeconds,
        finishStatus = finishStatus,
        race = race,
        rider = rider,
    )
}
