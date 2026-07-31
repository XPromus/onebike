package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.placement.Placement
import com.xpromus.onebike_backend.placement.PlacementRepository
import com.xpromus.onebike_backend.placement.mapper.toPlacementDescriptorDto
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.dto.PostRiderDto
import com.xpromus.onebike_backend.rider.dto.PutRiderDto
import com.xpromus.onebike_backend.rider.dto.RiderFilter
import com.xpromus.onebike_backend.rider.mapper.toEntity
import com.xpromus.onebike_backend.rider.mapper.toGetRiderDto
import com.xpromus.onebike_backend.rider.mapper.toGetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.mapper.toNewEntity
import com.xpromus.onebike_backend.rider.specification.RiderSpecification
import com.xpromus.onebike_backend.team.TeamRepository
import com.xpromus.onebike_backend.team.mapper.toTeamDescriptorDto
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RiderService(
    private val riderRepository: RiderRepository,
    private val nationRepository: NationRepository,
    private val teamRepository: TeamRepository,
    private val placementRepository: PlacementRepository,
) {

    @Transactional(readOnly = true)
    fun findRiders(
        filter: RiderFilter,
        pageable: Pageable
    ): Page<GetRiderDto> {
        val spec = RiderSpecification.withFilter(filter)
        val riders = riderRepository.findAll(spec, pageable)
        val riderIds = riders.content.map { it.id!! }

        val placementIds: Map<Long, List<Long>> = placementRepository
            .findIdsByRiderIds(riderIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { it[1] as Long }
            )

        return riders.map { rider ->
            rider.toGetRiderDto(
                placementIds = placementIds[rider.id] ?: emptyList(),
                nationId = rider.nation.id!!,
                teamId = rider.team?.id
            )
        }
    }

    @Transactional(readOnly = true)
    fun findRidersWithChildren(
        filter: RiderFilter,
        pageable: Pageable
    ): Page<GetRiderWithChildrenDto> {
        val spec = RiderSpecification.withFilter(filter)
        val riders = riderRepository.findAll(spec, pageable)
        val riderIds = riders.content.map { it.id!! }

        val placementDescriptors = placementRepository
            .findByRiderIds(riderIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { (it[1] as Placement).toPlacementDescriptorDto() }
            )

        val nationIds = riders.map { it.nation.id!! }.toSet()
        val nations = nationRepository
            .findAllById(nationIds)
            .associateBy { it.id }

        val teamIds = riders.mapNotNull { it.team?.id }.toSet()
        val teams = teamRepository
            .findAllById(teamIds)
            .associateBy { it.id }

        return riders.map { rider ->
            rider.toGetRiderWithChildrenDto(
                placements = placementDescriptors[rider.id] ?: emptyList(),
                nation = nations[rider.nation.id]!!.toNationDescriptorDto(),
                team = rider.team?.id?.let { teams[it]?.toTeamDescriptorDto() }
            )
        }
    }

    @Transactional
    fun putRider(
        id: Long,
        putRiderDto: PutRiderDto
    ): Pair<GetRiderDto, Boolean> {
        val targetNation = nationRepository
            .findById(putRiderDto.nationId)
            .orElseThrow { EntityNotFoundException() }
        val targetTeam = putRiderDto.teamId?.let {
            teamRepository.findById(it).orElse(null)
        }

        val existingRider = riderRepository.findByIdOrNull(id)
        val riderToSave = existingRider?.let {
            putRiderDto.toEntity(
                original = it,
                nation = targetNation,
                team = targetTeam
            )
        } ?: run {
            putRiderDto.toNewEntity(
                nation = targetNation,
                team = targetTeam
            )
        }

        val savedRider = riderRepository.save(riderToSave)
        val placementIds = placementRepository.findIdsByRiderIds(listOf(savedRider.id!!)).map { it[1] as Long }
        return savedRider.toGetRiderDto(
            placementIds = placementIds,
            nationId = savedRider.nation.id!!,
            teamId = savedRider.team?.id
        ) to (existingRider == null)
    }

    @Transactional
    fun createRider(
        postRiderDto: PostRiderDto
    ): GetRiderDto {
        val targetNation = nationRepository
            .findById(postRiderDto.nationId)
            .orElseThrow { EntityNotFoundException() }
        val targetTeam = postRiderDto.teamId?.let {
            teamRepository.findById(it).orElse(null)
        }
        val riderToSave = postRiderDto.toNewEntity(
            nation = targetNation,
            team = targetTeam
        )

        val savedRider = riderRepository.save(riderToSave)
        return savedRider.toGetRiderDto(
            placementIds = emptyList(),
            nationId = savedRider.nation.id!!,
            teamId = savedRider.team?.id
        )
    }

    @Transactional
    fun deleteRider(
        id: Long
    ) {
        riderRepository.deleteById(id)
    }

}
