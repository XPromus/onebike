package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.team.dto.GetTeamDto
import com.xpromus.onebike_backend.team.dto.PutTeamDto
import com.xpromus.onebike_backend.team.mapper.toEntity
import com.xpromus.onebike_backend.team.mapper.toGetTeamDto
import com.xpromus.onebike_backend.team.mapper.toGetTeamDtoList
import com.xpromus.onebike_backend.team.mapper.toNewEntity
import com.xpromus.onebike_backend.util.SortDirection
import com.xpromus.onebike_backend.util.toSortDir
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class TeamService(
    private val teamRepository: TeamRepository,
    private val nationRepository: NationRepository
) {

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
