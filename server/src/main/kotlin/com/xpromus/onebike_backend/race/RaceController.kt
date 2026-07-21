package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.GetRaceWithChildrenDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto
import com.xpromus.onebike_backend.util.SortDirection
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus

@RestController
@RequestMapping("/races")
class RaceController(
    private val raceService: RaceService
) {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun getRaces(
        @RequestParam(name = "sortBy", defaultValue = "raceName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetRaceDto> {
        return raceService.getAllRaces(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @GetMapping("/full")
    @ResponseStatus(value = HttpStatus.OK)
    fun getRacesWithChildren(
        @RequestParam(name = "sortBy", defaultValue = "raceName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetRaceWithChildrenDto> {
        return raceService.getRacesWithChildren(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun putRace(
        putRaceDto: PutRaceDto
    ): GetRaceDto {
        return raceService.putRace(putRaceDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun deleteRace(
        @PathVariable id: Long
    ) {
        raceService.deleteRace(id)
    }

}
