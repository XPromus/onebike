package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.GetRaceWithChildrenDto
import com.xpromus.onebike_backend.race.dto.PostRaceDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto
import com.xpromus.onebike_backend.race.dto.RaceFilter
import com.xpromus.onebike_backend.race.sort.RaceSortField
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
@RequestMapping("/api/v1/races")
class RaceController(
    private val raceService: RaceService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getRaces(
        @Valid @ModelAttribute filter: RaceFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "RACE_NAME") sortBy: RaceSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection,
    ): ResponseEntity<Page<GetRaceDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val races = raceService.findRaces(
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(races)
    }

    @GetMapping("/full")
    @ResponseStatus(HttpStatus.OK)
    fun getRacesWithChildren(
        @Valid @ModelAttribute filter: RaceFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "RACE_NAME") sortBy: RaceSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection,
    ): ResponseEntity<Page<GetRaceWithChildrenDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val races = raceService.findRacesWithChildren(
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(races)
    }

    @PutMapping("/{id}")
    fun putRace(
        @PathVariable id: Long,
        @Valid @RequestBody putRaceDto: PutRaceDto,
    ): ResponseEntity<GetRaceDto> {
        val (body, wasCreated) = raceService.putRace(id, putRaceDto)
        val status = if (wasCreated) HttpStatus.CREATED else HttpStatus.OK
        return ResponseEntity(body, status)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRace(
        @Valid @RequestBody postRaceDto: PostRaceDto,
    ): ResponseEntity<GetRaceDto> {
        val savedRace = raceService.createRace(postRaceDto)
        val location = URI.create("/api/v1/races/${savedRace.id}")
        return ResponseEntity.created(location).body(savedRace)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRace(
        @PathVariable id: Long,
    ) {
        raceService.deleteRace(id)
    }

}
