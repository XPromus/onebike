package com.xpromus.onebike_backend.team.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.rider.mapper.toRiderDescriptorDto
import com.xpromus.onebike_backend.team.Team
import com.xpromus.onebike_backend.team.dto.GetTeamDto
import com.xpromus.onebike_backend.team.dto.GetTeamWithChildrenDto
import com.xpromus.onebike_backend.team.dto.PutTeamDto
import com.xpromus.onebike_backend.team.dto.TeamDescriptorDto

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

fun Team.toGetTeamWithChildrenDto(): GetTeamWithChildrenDto {
    return GetTeamWithChildrenDto(
        id = id!!,
        teamName = teamName,
        shortName = shortName,
        teamDescription = teamDescription,
        nation = nationality.toNationDescriptorDto(),
        riders = riders.map { it.toRiderDescriptorDto() }
    )
}

fun List<Team>.toGetTeamWithChildrenDtoList(): List<GetTeamWithChildrenDto> {
    return map {
        it.toGetTeamWithChildrenDto()
    }
}

fun Team.toTeamDescriptorDto(): TeamDescriptorDto {
    return TeamDescriptorDto(
        id = id!!,
        teamName = teamName,
        shortName = shortName,
        teamDescription = teamDescription
    )
}

fun List<Team>.toTeamDescriptorDtoList(): List<TeamDescriptorDto> {
    return map {
        it.toTeamDescriptorDto()
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
