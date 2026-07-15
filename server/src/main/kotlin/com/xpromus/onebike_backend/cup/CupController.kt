package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.util.SortDirection
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RestController
@RequestMapping("/cups")
class CupController(
    private val cupService: CupService
) {

    @GetMapping
    fun getCups(
        @RequestParam(name = "sortBy", defaultValue = "cupName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetCupWithChildrenDto> {
        return cupService.getAll(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @GetMapping("/nation/{id}")
    fun getCupsInNation(
        @PathVariable id: Long,
        @RequestParam(name = "sortBy", defaultValue = "cupName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetCupWithChildrenDto> {
        return cupService.getCupsInNation(
            id = id,
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @GetMapping("/name/{name}")
    fun getCupsByName(
        @PathVariable name: String,
        @RequestParam(name = "sortBy", defaultValue = "cupName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetCupWithChildrenDto> {
        return cupService.getCupsByName(
            name = name,
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @PutMapping
    fun putCup(
        @RequestBody putCupDto: PutCupDto
    ): GetCupWithChildrenDto {
        return cupService.putCupDto(putCupDto)
    }

    @DeleteMapping("/{id}")
    fun deleteCup(
        @PathVariable id: Long
    ) {
        cupService.deleteCup(id)
    }

}
