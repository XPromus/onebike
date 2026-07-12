package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/races")
class RaceController(
    private val raceService: RaceService
) {

    @GetMapping
    fun getAllRaces(): List<GetRaceDto> {
        return raceService.getAllRaces()
    }

    @PutMapping
    fun putRace(
        putRaceDto: PutRaceDto
    ): GetRaceDto {
        return raceService.putRace(putRaceDto)
    }

    @DeleteMapping("/{id}")
    fun deleteRace(
        @PathVariable id: Long
    ) {
        raceService.deleteRace(id)
    }

}
