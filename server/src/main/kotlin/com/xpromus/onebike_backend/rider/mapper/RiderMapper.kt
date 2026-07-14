package com.xpromus.onebike_backend.rider.mapper

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.dto.PutRiderDto
import com.xpromus.onebike_backend.team.mapper.toTeamDescriptorDto

fun Rider.toGetRiderDto(): GetRiderDto {
    return GetRiderDto(
        id = id!!,
        firstName = firstName,
        lastName = lastName,
        placementIDs = placements.map { it.id!! },
        nationId = nation.id!!
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
        nation = nation.toNationDescriptorDto(),
        team = team?.toTeamDescriptorDto()
    )
}

fun List<Rider>.toGetRiderWithChildrenDtoList(): List<GetRiderWithChildrenDto> {
    return map {
        it.toGetRiderWithChildrenDto()
    }
}

fun PutRiderDto.toEntity(
    original: Rider,
    nation: Nation
): Rider {
    return Rider(
        id = original.id,
        firstName = firstName,
        lastName = lastName,
        placements = original.placements,
        nation = nation
    )
}

fun PutRiderDto.toNewEntity(
    nation: Nation
): Rider {
    return Rider(
        firstName = firstName,
        lastName = lastName,
        nation = nation
    )
}
