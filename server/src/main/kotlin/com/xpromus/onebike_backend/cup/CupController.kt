package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/cups")
class CupController(
    private val cupService: CupService
) {

    @GetMapping
    fun getCups(): List<GetCupDto> {
        return cupService.getAll()
    }

    @GetMapping("/nation/{id}")
    fun getCupsInNation(
        @PathVariable id: Long
    ): List<GetCupDto> {
        return cupService.getCupsInNation(id)
    }

    @GetMapping("/name/{name}")
    fun getCupsByName(
        @PathVariable name: String
    ): List<GetCupDto> {
        return cupService.getCupsByName(name)
    }

    @PutMapping
    fun putCup(
        @RequestBody putCupDto: PutCupDto
    ): GetCupDto {
        return cupService.putCupDto(putCupDto)
    }

    @DeleteMapping("/{id}")
    fun deleteCup(
        id: Long
    ) {
        cupService.deleteCup(id)
    }

}
