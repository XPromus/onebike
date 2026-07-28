package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.cup.CupRepository
import com.xpromus.onebike_backend.cup.mapper.toCupDescriptorDto
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.placement.Placement
import com.xpromus.onebike_backend.placement.PlacementRepository
import com.xpromus.onebike_backend.placement.mapper.toPlacementDescriptorDto
import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.GetRaceWithChildrenDto
import com.xpromus.onebike_backend.race.dto.PostRaceDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto
import com.xpromus.onebike_backend.race.dto.RaceFilter
import com.xpromus.onebike_backend.race.mapper.toEntity
import com.xpromus.onebike_backend.race.mapper.toGetRaceDto
import com.xpromus.onebike_backend.race.mapper.toGetRaceWithChildrenDto
import com.xpromus.onebike_backend.race.mapper.toNewEntity
import com.xpromus.onebike_backend.race.specification.RaceSpecification
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RaceService(
    private val raceRepository: RaceRepository,
    private val nationRepository: NationRepository,
    private val cupRepository: CupRepository,
    private val placementRepository: PlacementRepository,
) {

    @Transactional(readOnly = true)
    fun findRaces(
        filter: RaceFilter,
        pageable: Pageable,
    ): Page<GetRaceDto> {
        val spec = RaceSpecification.withFilter(filter)
        val races = raceRepository.findAll(spec, pageable)
        val raceIds = races.content.map { it.id!! }

        val placementIds: Map<Long, List<Long>> = placementRepository
            .findIdsByRaceIds(raceIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { it[1] as Long }
            )

        return races.map { race ->
            race.toGetRaceDto(
                placementIds = placementIds[race.id] ?: emptyList(),
                nationId = race.nation.id!!,
                cupId = race.cup?.id
            )
        }
    }

    @Transactional(readOnly = true)
    fun findRacesWithChildren(
        filter: RaceFilter,
        pageable: Pageable,
    ): Page<GetRaceWithChildrenDto> {
        val spec = RaceSpecification.withFilter(filter)
        val races = raceRepository.findAll(spec, pageable)
        val raceIds = races.content.map { it.id!! }

        val placementDescriptors = placementRepository
            .findByRaceIds(raceIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { (it[1] as Placement).toPlacementDescriptorDto() }
            )

        val nationIds = races.map { it.nation.id!! }.toSet()
        val nations = nationRepository
            .findAllById(nationIds)
            .associateBy { it.id }

        val cupIds = races.mapNotNull { it.cup?.id }.toSet()
        val cups = cupRepository
            .findAllById(cupIds)
            .associateBy { it.id }

        return races.map { race ->
            race.toGetRaceWithChildrenDto(
                placements = placementDescriptors[race.id] ?: emptyList(),
                nation = nations[race.nation.id]!!.toNationDescriptorDto(),
                cup = race.cup?.id?.let { cups[it]?.toCupDescriptorDto() }
            )
        }
    }

    @Transactional
    fun putRace(
        id: Long,
        putRaceDto: PutRaceDto,
    ): Pair<GetRaceDto, Boolean> {
        val targetNation = nationRepository
            .findById(putRaceDto.nationId)
            .orElseThrow { EntityNotFoundException() }
        val targetCup = putRaceDto.cupId?.let {
            cupRepository.findById(it).orElse(null)
        }

        val existingRace = raceRepository.findByIdOrNull(id)
        val raceToSave = existingRace?.let {
            putRaceDto.toEntity(
                original = it,
                nation = targetNation,
                cup = targetCup
            )
        } ?: run {
            putRaceDto.toNewEntity(
                nation = targetNation,
                cup = targetCup
            )
        }

        val savedRace = raceRepository.save(raceToSave)
        val placementIds = placementRepository
            .findIdsByRaceIds(
                listOf(savedRace.id!!)
            ).map {
                it[1] as Long
            }

        return savedRace.toGetRaceDto(
            placementIds = placementIds,
            nationId = savedRace.nation.id!!,
            cupId = savedRace.cup?.id
        ) to (existingRace == null)
    }

    @Transactional
    fun createRace(
        postRaceDto: PostRaceDto,
    ): GetRaceDto {
        val targetNation = nationRepository
            .findById(postRaceDto.nationId)
            .orElseThrow { EntityNotFoundException() }
        val targetCup = postRaceDto.cupId?.let {
            cupRepository.findById(it).orElse(null)
        }

        val raceToSave = postRaceDto.toNewEntity(
            nation = targetNation,
            cup = targetCup
        )

        val savedRace = raceRepository.save(raceToSave)
        val placementIds = placementRepository
            .findIdsByRaceIds(
                listOf(savedRace.id!!)
            ).map {
                it[1] as Long
            }

        return savedRace.toGetRaceDto(
            placementIds = placementIds,
            nationId = savedRace.nation.id!!,
            cupId = savedRace.cup?.id
        )
    }

    @Transactional
    fun deleteRace(
        id: Long,
    ) {
        raceRepository.deleteById(id)
    }

}
