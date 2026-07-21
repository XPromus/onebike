package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.team.dto.GetTeamDto
import com.xpromus.onebike_backend.team.dto.GetTeamWithChildrenDto
import com.xpromus.onebike_backend.team.dto.PutTeamDto
import com.xpromus.onebike_backend.team.mapper.toEntity
import com.xpromus.onebike_backend.team.mapper.toGetTeamDto
import com.xpromus.onebike_backend.team.mapper.toGetTeamDtoList
import com.xpromus.onebike_backend.team.mapper.toGetTeamWithChildrenDtoList
import com.xpromus.onebike_backend.team.mapper.toNewEntity
import com.xpromus.onebike_backend.util.SortDirection
import com.xpromus.onebike_backend.util.toSortDir
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeamService(
    private val teamRepository: TeamRepository,
    private val nationRepository: NationRepository
) {

    @Transactional(readOnly = true)
    fun getTeams(
        sortBy: String,
        sortDirection: SortDirection
    ): List<GetTeamDto> {
        return teamRepository.findAll(
            Sort.by(
                sortDirection.toSortDir(),
                sortBy
            )
        ).toGetTeamDtoList()
    }

    @Transactional(readOnly = true)
    fun getTeamsWithChildren(
        sortBy: String,
        sortDirection: SortDirection
    ): List<GetTeamWithChildrenDto> {
        return teamRepository.findAll(
            Sort.by(
                sortDirection.toSortDir(),
                sortBy
            )
        ).toGetTeamWithChildrenDtoList()
    }

    @Transactional
    fun putTeam(
        putTeamDto: PutTeamDto
    ): GetTeamDto {
        val targetNation: Nation = nationRepository.findById(putTeamDto.nationId).orElseThrow {
            EntityNotFoundException()
        }

        val team: Team = putTeamDto.id?.let {
            teamRepository.findById(it).orElse(null)
        }?.let {
            putTeamDto.toEntity(
                original = it,
                nation = targetNation
            )
        } ?: run {
            putTeamDto.toNewEntity(
                nation = targetNation
            )
        }

        return teamRepository.save(
            team
        ).toGetTeamDto()
    }

    @Transactional
    fun deleteTeam(
        id: Long
    ) {
        teamRepository.deleteById(id)
    }

}
