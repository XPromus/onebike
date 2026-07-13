package com.xpromus.onebike_backend.placement

import com.xpromus.onebike_backend.placement.dto.GetPlacementDto
import com.xpromus.onebike_backend.placement.dto.PutPlacementDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/placements")
class PlacementController(
    private val placementService: PlacementService
) {

    @GetMapping("/race/{id}")
    fun getPlacementsByRace(
        @PathVariable id: Long
    ): List<GetPlacementDto> {
        return placementService.getPlacementsByRace(id)
    }

    @GetMapping("/rider/{id}")
    fun getPlacementsByRider(
        @PathVariable id: Long
    ): List<GetPlacementDto> {
        return placementService.getCupPlacementsByRider(id)
    }

    @PutMapping
    fun putPlacement(
        putPlacementDto: PutPlacementDto
    ): GetPlacementDto {
        return placementService.putCupPlacement(putPlacementDto)
    }

    @DeleteMapping("/{id}")
    fun deletePlacement(
        @PathVariable id: Long
    ) {
        placementService.deleteCupPlacement(id)
    }

}
