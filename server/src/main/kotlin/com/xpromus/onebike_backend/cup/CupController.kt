package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.util.SortDirection
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus

@RestController
@RequestMapping("/cups")
class CupController(
    private val cupService: CupService
) {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun getCups(
        @RequestParam(name = "sortBy", defaultValue = "cupName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetCupDto> {
        return cupService.getCups(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @GetMapping("/full")
    @ResponseStatus(value = HttpStatus.OK)
    fun getCupsWithChildren(
        @RequestParam(name = "sortBy", defaultValue = "cupName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetCupWithChildrenDto> {
        return cupService.getCupWithChildren(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @GetMapping("/nation/{id}")
    @ResponseStatus(value = HttpStatus.OK)
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
    @ResponseStatus(value = HttpStatus.OK)
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
    @ResponseStatus(value = HttpStatus.OK)
    fun putCup(
        @RequestBody putCupDto: PutCupDto
    ): GetCupWithChildrenDto {
        return cupService.putCupDto(putCupDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun deleteCup(
        @PathVariable id: Long
    ) {
        cupService.deleteCup(id)
    }

}
