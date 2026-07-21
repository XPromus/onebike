package com.xpromus.onebike_backend.placement

import com.xpromus.onebike_backend.placement.dto.GetPlacementDto
import com.xpromus.onebike_backend.placement.dto.GetPlacementWithChildrenDto
import com.xpromus.onebike_backend.placement.dto.PutPlacementDto
import com.xpromus.onebike_backend.util.SortDirection
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/placements")
class PlacementController(
    private val placementService: PlacementService
) {

    @GetMapping("/race/{id}")
    @ResponseStatus(value = HttpStatus.OK)
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

    @GetMapping("/race/{id}/full")
    @ResponseStatus(value = HttpStatus.OK)
    fun getPlacementsByRaceWithChildren(
        @PathVariable id: Long,
        @RequestParam(name = "sortBy", defaultValue = "finishTimeInSeconds") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection
    ): List<GetPlacementWithChildrenDto> {
        return placementService.getPlacementsByRaceWithChildren(
            raceId = id,
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @GetMapping("/rider/{id}")
    @ResponseStatus(value = HttpStatus.OK)
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

    @GetMapping("/rider/{id}/full")
    @ResponseStatus(value = HttpStatus.OK)
    fun getPlacementsByRiderWithChildren(
        @PathVariable id: Long,
        @RequestParam(name = "sortBy", defaultValue = "finishTimeInSeconds") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection
    ): List<GetPlacementWithChildrenDto> {
        return placementService.getCupPlacementsByRiderWithChildren(
            riderId = id,
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun putPlacement(
        putPlacementDto: PutPlacementDto
    ): GetPlacementDto {
        return placementService.putCupPlacement(putPlacementDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun deletePlacement(
        @PathVariable id: Long
    ) {
        placementService.deleteCupPlacement(id)
    }

}
