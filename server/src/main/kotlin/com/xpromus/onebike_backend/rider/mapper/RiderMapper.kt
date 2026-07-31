package com.xpromus.onebike_backend.rider.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.placement.dto.PlacementDescriptorDto
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.dto.PostRiderDto
import com.xpromus.onebike_backend.rider.dto.PutRiderDto
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto
import com.xpromus.onebike_backend.team.Team
import com.xpromus.onebike_backend.team.dto.TeamDescriptorDto

fun Rider.toGetRiderDto(
    placementIds: List<Long>,
    nationId: Long,
    teamId: Long?
): GetRiderDto {
    return GetRiderDto(
        id = id!!,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth,
        placementIds = placementIds,
        nationId = nationId,
        teamId = teamId
    )
}

fun Rider.toGetRiderWithChildrenDto(
    placements: List<PlacementDescriptorDto>,
    nation: NationDescriptorDto,
    team: TeamDescriptorDto?
): GetRiderWithChildrenDto {
    return GetRiderWithChildrenDto(
        id = id!!,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth,
        placements = placements,
        nation = nation,
        team = team,
    )
}

fun Rider.toRiderDescriptorDto(): RiderDescriptorDto {
    return RiderDescriptorDto(
        id = id!!,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth
    )
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

fun PostRiderDto.toNewEntity(
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
