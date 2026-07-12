package com.xpromus.onebike_backend.cup.placement

import com.xpromus.onebike_backend.cup.placement.dto.GetPlacementDto
import com.xpromus.onebike_backend.cup.placement.dto.PutPlacementDto
import com.xpromus.onebike_backend.cup.placement.mapper.toEntity
import com.xpromus.onebike_backend.cup.placement.mapper.toGetPlacementDto
import com.xpromus.onebike_backend.cup.placement.mapper.toGetPlacementDtoList
import com.xpromus.onebike_backend.cup.placement.mapper.toNewEntity
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.RaceRepository
import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.RiderRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PlacementService(
    private val placementRepository: PlacementRepository,
    private val riderRepository: RiderRepository,
    private val raceRepository: RaceRepository
) {

    fun getPlacementsByRace(
        raceId: Long
    ): List<GetPlacementDto> {
        return placementRepository.findPlacementsByRaceId(
            raceId = raceId
        ).toGetPlacementDtoList()
    }

    fun getCupPlacementsByRider(
        riderId: Long
    ): List<GetPlacementDto> {
        return placementRepository.getPlacementsByRiderId(
            riderId = riderId
        ).toGetPlacementDtoList()
    }

    @Transactional
    fun putCupPlacement(
        putPlacementDto: PutPlacementDto
    ): GetPlacementDto {
        val targetRace: Race = raceRepository
            .findById(putPlacementDto.targetRaceId)
            .orElseThrow {
                EntityNotFoundException()
            }
        val targetRider: Rider = riderRepository
            .findById(putPlacementDto.id)
            .orElseThrow {
                EntityNotFoundException()
            }

        val placement: Placement = putPlacementDto.id?.let {
            placementRepository.findById(it).orElse(null)
        }?.let {
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

        return placementRepository.save(
            placement
        ).toGetPlacementDto()
    }

    @Transactional
    fun deleteCupPlacement(
        id: Long
    ) {
        placementRepository.deleteById(id)
    }

}
