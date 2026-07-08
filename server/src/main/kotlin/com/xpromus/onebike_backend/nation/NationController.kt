package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.nation.dto.CreateNationDto
import com.xpromus.onebike_backend.nation.dto.GetNationDto
import com.xpromus.onebike_backend.nation.dto.UpdateNationDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class NationController(
    private val nationService: NationService
) {

    @GetMapping
    fun getNations(): List<GetNationDto> {
        return nationService.getNations()
    }

    @PostMapping
    fun createNation(
        @RequestBody createNationDto: CreateNationDto
    ): GetNationDto {
        return nationService.createNation(createNationDto)
    }

    @PutMapping
    fun updateNation(
        @RequestBody updateNationDto: UpdateNationDto
    ): GetNationDto {
        return nationService.updateNation(updateNationDto)
    }

    @DeleteMapping("/{id}")
    fun deleteNation(
        @PathVariable id: Long
    ) {
        nationService.deleteNation(id)
    }

}
