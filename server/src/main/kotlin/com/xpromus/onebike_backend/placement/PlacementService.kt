package com.xpromus.onebike_backend.placement

import com.xpromus.onebike_backend.placement.dto.GetPlacementDto
import com.xpromus.onebike_backend.placement.dto.GetPlacementWithChildrenDto
import com.xpromus.onebike_backend.placement.dto.PlacementFilter
import com.xpromus.onebike_backend.placement.dto.PostPlacementDto
import com.xpromus.onebike_backend.placement.dto.PutPlacementDto
import com.xpromus.onebike_backend.placement.mapper.toEntity
import com.xpromus.onebike_backend.placement.mapper.toGetPlacementDto
import com.xpromus.onebike_backend.placement.mapper.toGetPlacementWithChildrenDto
import com.xpromus.onebike_backend.placement.mapper.toNewEntity
import com.xpromus.onebike_backend.placement.specification.PlacementSpecification
import com.xpromus.onebike_backend.race.RaceRepository
import com.xpromus.onebike_backend.race.mapper.toRaceDescriptorDto
import com.xpromus.onebike_backend.rider.RiderRepository
import com.xpromus.onebike_backend.rider.mapper.toRiderDescriptorDto
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlacementService(
    private val placementRepository: PlacementRepository,
    private val riderRepository: RiderRepository,
    private val raceRepository: RaceRepository,
) {

    @Transactional(readOnly = true)
    fun findPlacements(
        filter: PlacementFilter,
        pageable: Pageable,
    ): Page<GetPlacementDto> {
        val spec = PlacementSpecification.withFilter(filter)
        val placements = placementRepository.findAll(spec, pageable)

        return placements.map { placement ->
            placement.toGetPlacementDto(
                riderId = placement.rider.id!!,
                raceId = placement.race.id!!
            )
        }
    }

    @Transactional(readOnly = true)
    fun findPlacementsWithChildren(
        filter: PlacementFilter,
        pageable: Pageable,
    ): Page<GetPlacementWithChildrenDto> {
        val spec = PlacementSpecification.withFilter(filter)
        val placements = placementRepository.findAll(spec, pageable)

        val riderIds = placements.map { it.rider.id!! }.toSet()
        val riders = riderRepository
            .findAllById(riderIds)
            .associateBy { it.id }

        val raceIds = placements.map { it.race.id!! }.toSet()
        val races = raceRepository
            .findAllById(raceIds)
            .associateBy { it.id }

        return placements.map { placement ->
            placement.toGetPlacementWithChildrenDto(
                rider = riders[placement.rider.id]!!.toRiderDescriptorDto(),
                race = races[placement.race.id]!!.toRaceDescriptorDto()
            )
        }
    }

    @Transactional(readOnly = true)
    fun findPlacementsByRace(
        raceId: Long,
        filter: PlacementFilter,
        pageable: Pageable,
    ): Page<GetPlacementDto> {
        val spec = PlacementSpecification.withFilter(filter, raceId = raceId)
        val placements = placementRepository.findAll(spec, pageable)

        return placements.map { placement ->
            placement.toGetPlacementDto(
                riderId = placement.rider.id!!,
                raceId = placement.race.id!!
            )
        }
    }

    @Transactional(readOnly = true)
    fun findPlacementsByRaceWithChildren(
        raceId: Long,
        filter: PlacementFilter,
        pageable: Pageable,
    ): Page<GetPlacementWithChildrenDto> {
        val spec = PlacementSpecification.withFilter(filter, raceId = raceId)
        val placements = placementRepository.findAll(spec, pageable)

        val riderIds = placements.map { it.rider.id!! }.toSet()
        val riders = riderRepository
            .findAllById(riderIds)
            .associateBy { it.id }

        val race = raceRepository.findById(raceId).orElseThrow { EntityNotFoundException() }

        return placements.map { placement ->
            placement.toGetPlacementWithChildrenDto(
                rider = riders[placement.rider.id]!!.toRiderDescriptorDto(),
                race = race.toRaceDescriptorDto()
            )
        }
    }

    @Transactional(readOnly = true)
    fun findPlacementsByRider(
        riderId: Long,
        filter: PlacementFilter,
        pageable: Pageable,
    ): Page<GetPlacementDto> {
        val spec = PlacementSpecification.withFilter(filter, riderId = riderId)
        val placements = placementRepository.findAll(spec, pageable)

        return placements.map { placement ->
            placement.toGetPlacementDto(
                riderId = placement.rider.id!!,
                raceId = placement.race.id!!
            )
        }
    }

    @Transactional(readOnly = true)
    fun findPlacementsByRiderWithChildren(
        riderId: Long,
        filter: PlacementFilter,
        pageable: Pageable,
    ): Page<GetPlacementWithChildrenDto> {
        val spec = PlacementSpecification.withFilter(filter, riderId = riderId)
        val placements = placementRepository.findAll(spec, pageable)

        val rider = riderRepository.findById(riderId).orElseThrow { EntityNotFoundException() }

        val raceIds = placements.map { it.race.id!! }.toSet()
        val races = raceRepository
            .findAllById(raceIds)
            .associateBy { it.id }

        return placements.map { placement ->
            placement.toGetPlacementWithChildrenDto(
                rider = rider.toRiderDescriptorDto(),
                race = races[placement.race.id]!!.toRaceDescriptorDto()
            )
        }
    }

    @Transactional
    fun putPlacement(
        id: Long,
        putPlacementDto: PutPlacementDto,
    ): Pair<GetPlacementDto, Boolean> {
        val targetRace = raceRepository
            .findById(putPlacementDto.raceId)
            .orElseThrow { EntityNotFoundException() }
        val targetRider = riderRepository
            .findById(putPlacementDto.riderId)
            .orElseThrow { EntityNotFoundException() }

        val existingPlacement = placementRepository.findByIdOrNull(id)
        val placementToSave = existingPlacement?.let {
            putPlacementDto.toEntity(
                original = it,
                race = targetRace,
                rider = targetRider
            )
        } ?: run {
            putPlacementDto.toNewEntity(
                race = targetRace,
                rider = targetRider
            )
        }

        val savedPlacement = placementRepository.save(placementToSave)
        return savedPlacement.toGetPlacementDto(
            riderId = savedPlacement.rider.id!!,
            raceId = savedPlacement.race.id!!
        ) to (existingPlacement == null)
    }

    @Transactional
    fun createPlacement(
        postPlacementDto: PostPlacementDto,
    ): GetPlacementDto {
        val targetRace = raceRepository
            .findById(postPlacementDto.raceId)
            .orElseThrow { EntityNotFoundException() }
        val targetRider = riderRepository
            .findById(postPlacementDto.riderId)
            .orElseThrow { EntityNotFoundException() }

        val placementToSave = postPlacementDto.toNewEntity(
            race = targetRace,
            rider = targetRider
        )

        val savedPlacement = placementRepository.save(placementToSave)
        return savedPlacement.toGetPlacementDto(
            riderId = savedPlacement.rider.id!!,
            raceId = savedPlacement.race.id!!
        )
    }

    @Transactional
    fun deletePlacement(
        id: Long,
    ) {
        placementRepository.deleteById(id)
    }

}
