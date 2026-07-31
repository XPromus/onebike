package com.xpromus.onebike_backend.placement

import com.xpromus.onebike_backend.placement.dto.GetPlacementDto
import com.xpromus.onebike_backend.placement.dto.GetPlacementWithChildrenDto
import com.xpromus.onebike_backend.placement.dto.PlacementFilter
import com.xpromus.onebike_backend.placement.dto.PostPlacementDto
import com.xpromus.onebike_backend.placement.dto.PutPlacementDto
import com.xpromus.onebike_backend.placement.sort.PlacementSortField
import com.xpromus.onebike_backend.util.SortDirection
import com.xpromus.onebike_backend.util.toSortDir
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/placements")
class PlacementController(
    private val placementService: PlacementService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getPlacements(
        @Valid @ModelAttribute filter: PlacementFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "FINISH_TIME_IN_SECONDS") sortBy: PlacementSortField,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection,
    ): ResponseEntity<Page<GetPlacementDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val placements = placementService.findPlacements(
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(placements)
    }

    @GetMapping("/full")
    @ResponseStatus(HttpStatus.OK)
    fun getPlacementsWithChildren(
        @Valid @ModelAttribute filter: PlacementFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "FINISH_TIME_IN_SECONDS") sortBy: PlacementSortField,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection,
    ): ResponseEntity<Page<GetPlacementWithChildrenDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val placements = placementService.findPlacementsWithChildren(
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(placements)
    }

    @GetMapping("/race/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPlacementsByRace(
        @PathVariable id: Long,
        @Valid @ModelAttribute filter: PlacementFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "FINISH_TIME_IN_SECONDS") sortBy: PlacementSortField,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection,
    ): ResponseEntity<Page<GetPlacementDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val placements = placementService.findPlacementsByRace(
            raceId = id,
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(placements)
    }

    @GetMapping("/race/{id}/full")
    @ResponseStatus(HttpStatus.OK)
    fun getPlacementsByRaceWithChildren(
        @PathVariable id: Long,
        @Valid @ModelAttribute filter: PlacementFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "FINISH_TIME_IN_SECONDS") sortBy: PlacementSortField,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection,
    ): ResponseEntity<Page<GetPlacementWithChildrenDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val placements = placementService.findPlacementsByRaceWithChildren(
            raceId = id,
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(placements)
    }

    @GetMapping("/rider/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPlacementsByRider(
        @PathVariable id: Long,
        @Valid @ModelAttribute filter: PlacementFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "FINISH_TIME_IN_SECONDS") sortBy: PlacementSortField,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection,
    ): ResponseEntity<Page<GetPlacementDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val placements = placementService.findPlacementsByRider(
            riderId = id,
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(placements)
    }

    @GetMapping("/rider/{id}/full")
    @ResponseStatus(HttpStatus.OK)
    fun getPlacementsByRiderWithChildren(
        @PathVariable id: Long,
        @Valid @ModelAttribute filter: PlacementFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "FINISH_TIME_IN_SECONDS") sortBy: PlacementSortField,
        @RequestParam(name = "sortDir", defaultValue = "DESCENDING") sortDirection: SortDirection,
    ): ResponseEntity<Page<GetPlacementWithChildrenDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val placements = placementService.findPlacementsByRiderWithChildren(
            riderId = id,
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(placements)
    }

    @PutMapping("/{id}")
    fun putPlacement(
        @PathVariable id: Long,
        @Valid @RequestBody putPlacementDto: PutPlacementDto,
    ): ResponseEntity<GetPlacementDto> {
        val (body, wasCreated) = placementService.putPlacement(id, putPlacementDto)
        val status = if (wasCreated) HttpStatus.CREATED else HttpStatus.OK
        return ResponseEntity(body, status)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPlacement(
        @Valid @RequestBody postPlacementDto: PostPlacementDto,
    ): ResponseEntity<GetPlacementDto> {
        val savedPlacement = placementService.createPlacement(postPlacementDto)
        val location = URI.create("/api/v1/placements/${savedPlacement.id}")
        return ResponseEntity.created(location).body(savedPlacement)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePlacement(
        @PathVariable id: Long,
    ) {
        placementService.deletePlacement(id)
    }

}
