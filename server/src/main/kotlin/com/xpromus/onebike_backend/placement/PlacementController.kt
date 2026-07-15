package com.xpromus.onebike_backend.placement

import com.xpromus.onebike_backend.placement.dto.GetPlacementDto
import com.xpromus.onebike_backend.placement.dto.PutPlacementDto
import com.xpromus.onebike_backend.util.SortDirection
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/placements")
class PlacementController(
    private val placementService: PlacementService
) {

    @GetMapping("/race/{id}")
    fun getPlacementsByRace(
        @PathVariable id: Long,
        @RequestParam(name = "sortBy", defaultValue = "finishTimeInSeconds") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection
    ): List<GetPlacementDto> {
        return placementService.getPlacementsByRace(
            raceId = id,
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @GetMapping("/rider/{id}")
    fun getPlacementsByRider(
        @PathVariable id: Long,
        @RequestParam(name = "sortBy", defaultValue = "finishTimeInSeconds") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection
    ): List<GetPlacementDto> {
        return placementService.getCupPlacementsByRider(
            riderId = id,
            sortBy = sortBy,
            sortDirection = sortDirection
        )
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
