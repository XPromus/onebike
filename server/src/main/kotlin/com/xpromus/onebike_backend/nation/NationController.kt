package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.nation.dto.GetNationDto
import com.xpromus.onebike_backend.nation.dto.PutNationDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/nations")
class NationController(
    private val nationService: NationService
) {

    @GetMapping
    fun getNations(): List<GetNationDto> {
        return nationService.getNations()
    }

    @PutMapping
    fun updateNation(
        @RequestBody putNationDto: PutNationDto
    ): GetNationDto {
        return nationService.putNation(putNationDto)
    }

    @DeleteMapping("/{id}")
    fun deleteNation(
        @PathVariable id: Long
    ) {
        nationService.deleteNation(id)
    }

}
