package com.xpromus.onebike_backend.cup.placement

import com.xpromus.onebike_backend.cup.placement.dto.CreateCupPlacementDto
import com.xpromus.onebike_backend.cup.placement.dto.GetCupPlacementDto
import com.xpromus.onebike_backend.cup.placement.dto.UpdateCupPlacementDto
import com.xpromus.onebike_backend.cup.placement.mapper.createCupPlacementDtoToCupPlacement
import com.xpromus.onebike_backend.cup.placement.mapper.cupPlacementToGetCupPlacementDto
import com.xpromus.onebike_backend.cup.placement.mapper.updateCupPlacementDtoToCupPlacement
import org.springframework.stereotype.Service

@Service
class CupPlacementService(
    private val cupPlacementRepository: CupPlacementRepository
) {

    fun getCupPlacementsByCup(
        cupId: Long
    ): List<CupPlacement> {
        return cupPlacementRepository.getCupPlacementsByCup_Id(cupId)
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
