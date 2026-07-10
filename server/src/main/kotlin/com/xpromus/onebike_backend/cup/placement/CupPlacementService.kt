package com.xpromus.onebike_backend.cup.placement

import com.xpromus.onebike_backend.cup.CupRepository
import com.xpromus.onebike_backend.cup.placement.dto.CreateCupPlacementDto
import com.xpromus.onebike_backend.cup.placement.dto.GetCupPlacementDto
import com.xpromus.onebike_backend.cup.placement.dto.HttpPutCupPlacementDto
import com.xpromus.onebike_backend.cup.placement.dto.UpdateCupPlacementDto
import com.xpromus.onebike_backend.cup.placement.mapper.createCupPlacementDtoToCupPlacement
import com.xpromus.onebike_backend.cup.placement.mapper.cupPlacementToGetCupPlacementDto
import com.xpromus.onebike_backend.cup.placement.mapper.httpCupPlacementDtoToCupPlacement
import com.xpromus.onebike_backend.cup.placement.mapper.updateCupPlacementDtoToCupPlacement
import com.xpromus.onebike_backend.rider.RiderRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CupPlacementService(
    private val cupPlacementRepository: CupPlacementRepository,
    private val cupRepository: CupRepository,
    private val riderRepository: RiderRepository
) {

    fun getCupPlacementsByCup(
        cupId: Long
    ): List<CupPlacement> {
        return cupPlacementRepository.getCupPlacementsByCup_Id(cupId)
    }

    fun createCupPlacementFromHttp(
        httpPutCupPlacementDto: HttpPutCupPlacementDto
    ): GetCupPlacementDto {
        val targetCup = cupRepository.findById(httpPutCupPlacementDto.id).orElseThrow {
            EntityNotFoundException()
        }
        val targetRider = riderRepository.findById(httpPutCupPlacementDto.id).orElseThrow {
            EntityNotFoundException()
        }

        val newCupPlacement = httpCupPlacementDtoToCupPlacement(
            httpPutCupPlacementDto,
            targetCup,
            targetRider
        )
        return cupPlacementToGetCupPlacementDto(
            cupPlacementRepository.save(newCupPlacement)
        )
    }

    fun createCupPlacement(
        createCupPlacementDto: CreateCupPlacementDto
    ): GetCupPlacementDto {
        val newCupPlacement = createCupPlacementDtoToCupPlacement(createCupPlacementDto)
        return cupPlacementToGetCupPlacementDto(
            cupPlacementRepository.save(newCupPlacement)
        )
    }

    fun updateCupPlacement(
        updateCupPlacementDto: UpdateCupPlacementDto
    ): GetCupPlacementDto {
        val updatedCupPlacement = updateCupPlacementDtoToCupPlacement(updateCupPlacementDto)
        return cupPlacementToGetCupPlacementDto(
            cupPlacementRepository.save(updatedCupPlacement)
        )
    }

}
