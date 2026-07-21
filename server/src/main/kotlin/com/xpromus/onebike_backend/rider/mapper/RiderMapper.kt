package com.xpromus.onebike_backend.rider.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.placement.mapper.toPlacementDescriptorDtoList
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.dto.PutRiderDto
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto
import com.xpromus.onebike_backend.team.Team
import com.xpromus.onebike_backend.team.mapper.toTeamDescriptorDto

fun Rider.toGetRiderDto(): GetRiderDto {
    return GetRiderDto(
        id = id!!,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth,
        placementIDs = placements.map { it.id!! },
        nationId = nation.id!!,
        teamId = team?.id!!
    )
}

fun List<Rider>.toGetRiderDtoList(): List<GetRiderDto> = map {
    it.toGetRiderDto()
}

fun Rider.toGetRiderWithChildrenDto(): GetRiderWithChildrenDto {
    return GetRiderWithChildrenDto(
        id = id!!,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth,
        placements = placements.toPlacementDescriptorDtoList(),
        nation = nation.toNationDescriptorDto(),
        team = team?.toTeamDescriptorDto(),
    )
}

fun List<Rider>.toGetRiderWithChildrenDtoList(): List<GetRiderWithChildrenDto> {
    return map {
        it.toGetRiderWithChildrenDto()
    }
}

fun Rider.toRiderDescriptorDto(): RiderDescriptorDto {
    return RiderDescriptorDto(
        id = id!!,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth
    )
}

fun List<Rider>.toRiderDescriptorDtoList(): List<RiderDescriptorDto> {
    return map {
        it.toRiderDescriptorDto()
    }
}

fun PutRiderDto.toEntity(
    original: Rider,
    nation: Nation,
    team: Team?,
): Rider {
    return Rider(
        id = original.id,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth,
        placements = original.placements,
        nation = nation,
        team = team,
    )
}

fun PutRiderDto.toNewEntity(
    nation: Nation,
    team: Team?,
): Rider {
    return Rider(
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth,
        nation = nation,
        team = team,
    )
}
