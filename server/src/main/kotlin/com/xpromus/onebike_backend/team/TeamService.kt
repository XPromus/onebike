package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.RiderRepository
import com.xpromus.onebike_backend.rider.mapper.toRiderDescriptorDto
import com.xpromus.onebike_backend.team.dto.*
import com.xpromus.onebike_backend.team.mapper.toEntity
import com.xpromus.onebike_backend.team.mapper.toGetTeamDto
import com.xpromus.onebike_backend.team.mapper.toGetTeamWithChildrenDto
import com.xpromus.onebike_backend.team.mapper.toNewEntity
import com.xpromus.onebike_backend.team.specification.TeamSpecification
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeamService(
    private val teamRepository: TeamRepository,
    private val nationRepository: NationRepository,
    private val riderRepository: RiderRepository
) {

    @Transactional(readOnly = true)
    fun findTeams(
        filter: TeamFilter,
        pageable: Pageable
    ): Page<GetTeamDto> {
        val spec = TeamSpecification.withFilter(filter)
        val teams = teamRepository.findAll(spec, pageable)
        val teamIds = teams.content.map { it.id!! }

        val riderIds: Map<Long, List<Long>> = riderRepository
            .findIdsByTeamIds(teamIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { it[1] as Long }
            )

        return teams.map { team ->
            team.toGetTeamDto(
                nationId = team.nation.id!!,
                riderIds = riderIds[team.id] ?: emptyList()
            )
        }
    }

    @Transactional(readOnly = true)
    fun findTeamsWithChildren(
        filter: TeamFilter,
        pageable: Pageable
    ): Page<GetTeamWithChildrenDto> {
        val spec = TeamSpecification.withFilter(filter)
        val teams = teamRepository.findAll(spec, pageable)
        val teamIds = teams.content.map { it.id!! }

        val riderDescriptors = riderRepository
            .findByTeamIds(teamIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { (it[1] as Rider).toRiderDescriptorDto() }
            )

        val nationIds = teams.map { it.nation.id!! }.toSet()
        val nations = nationRepository
            .findAllById(nationIds)
            .associateBy { it.id }

        return teams.map { team ->
            team.toGetTeamWithChildrenDto(
                riders = riderDescriptors[team.id] ?: emptyList(),
                nation = nations[team.nation.id]!!.toNationDescriptorDto()
            )
        }
    }

    @Transactional
    fun putTeam(
        id: Long,
        putTeamDto: PutTeamDto
    ): Pair<GetTeamDto, Boolean> {
        val targetNation: Nation = nationRepository
            .findById(
                putTeamDto.nationId
            ).orElseThrow {
                EntityNotFoundException()
            }

        val existingTeam = teamRepository.findByIdOrNull(id)
        val teamToSave: Team = existingTeam?.let {
            putTeamDto.toEntity(
                original = it,
                nation = targetNation
            )
        } ?: run {
            putTeamDto.toNewEntity(
                nation = targetNation
            )
        }

        val savedTeam = teamRepository.save(teamToSave)
        val riderIds = riderRepository.findIdsByTeamId(savedTeam.id!!)

        return savedTeam.toGetTeamDto(
            riderIds = riderIds,
            nationId = savedTeam.nation.id!!,
        ) to (existingTeam == null)
    }

    @Transactional
    fun createTeam(
        postTeamDto: PostTeamDto
    ): GetTeamDto {
        val targetNation: Nation = nationRepository
            .findById(
                postTeamDto.nationId
            ).orElseThrow {
                EntityNotFoundException()
            }
        val teamToSave = postTeamDto.toNewEntity(
            nation = targetNation
        )

        val savedTeam = teamRepository.save(teamToSave)
        val riderIds = riderRepository.findIdsByTeamId(savedTeam.id!!)

        return savedTeam.toGetTeamDto(
            riderIds = riderIds,
            nationId = savedTeam.nation.id!!,
        )
    }

    @Transactional
    fun deleteTeam(
        id: Long
    ) {
        teamRepository.deleteById(id)
    }

}
