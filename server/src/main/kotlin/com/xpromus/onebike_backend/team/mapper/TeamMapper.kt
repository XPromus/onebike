package com.xpromus.onebike_backend.team.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.team.Team
import com.xpromus.onebike_backend.team.dto.GetTeamDto
import com.xpromus.onebike_backend.team.dto.PutTeamDto

fun Team.toGetTeamDto(): GetTeamDto {
    return GetTeamDto(
        id = id!!,
        teamName = teamName,
        shortName = shortName,
        teamDescription = teamDescription,
        nationalityId = nationality.id!!,
        riderIds = riders.map { it.id!! }
    )
}

fun List<Team>.toGetTeamDtoList(): List<GetTeamDto> {
    return map {
        it.toGetTeamDto()
    }
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
        nationality = nation,
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
        nationality = nation,
    )
}
