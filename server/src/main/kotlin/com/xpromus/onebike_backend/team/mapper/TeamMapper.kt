package com.xpromus.onebike_backend.team.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.NationDescriptorDto
import com.xpromus.onebike_backend.rider.dto.RiderDescriptorDto
import com.xpromus.onebike_backend.team.Team
import com.xpromus.onebike_backend.team.dto.*

fun Team.toGetTeamDto(
    nationId: Long,
    riderIds: List<Long>
): GetTeamDto {
    return GetTeamDto(
        id = id!!,
        teamName = teamName,
        shortName = shortName,
        teamDescription = teamDescription,
        nationId = nationId,
        riderIds = riderIds
    )
}

fun Team.toGetTeamWithChildrenDto(
    nation: NationDescriptorDto,
    riders: List<RiderDescriptorDto>
): GetTeamWithChildrenDto {
    return GetTeamWithChildrenDto(
        id = id!!,
        teamName = teamName,
        shortName = shortName,
        teamDescription = teamDescription,
        nation = nation,
        riders = riders
    )
}

fun Team.toTeamDescriptorDto(): TeamDescriptorDto {
    return TeamDescriptorDto(
        id = id!!,
        teamName = teamName,
        shortName = shortName,
        teamDescription = teamDescription
    )
}

fun PutTeamDto.toEntity(
    original: Team,
    nation: Nation
): Team {
    return Team(
        id = original.id,
        teamName = teamName,
        shortName = shortName,
        teamDescription = teamDescription,
        nation = nation,
        riders = original.riders
    )
}

fun PutTeamDto.toNewEntity(
    nation: Nation
): Team {
    return Team(
        teamName = teamName,
        shortName = shortName,
        teamDescription = teamDescription,
        nation = nation,
    )
}

fun PostTeamDto.toNewEntity(
    nation: Nation
): Team {
    return Team(
        teamName = teamName,
        shortName = shortName,
        teamDescription = teamDescription,
        nation = nation,
    )
}
