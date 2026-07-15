package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.nation.dto.GetNationDto
import com.xpromus.onebike_backend.nation.dto.GetNationWithChildrenDto
import com.xpromus.onebike_backend.nation.dto.PostNationExistsDto
import com.xpromus.onebike_backend.nation.dto.PutNationDto
import com.xpromus.onebike_backend.util.SortDirection
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/nations")
class NationController(
    private val nationService: NationService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getNations(
        @RequestParam(name = "sortBy", defaultValue = "longName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetNationDto> {
        return nationService.getNations(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @PostMapping("/exists")
    @ResponseStatus(HttpStatus.OK)
    fun checkIfNationExists(
        @RequestBody postNationExistsDto: PostNationExistsDto
    ): Boolean {
        return nationService.checkIfNationExists(postNationExistsDto)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateNation(
        @RequestBody putNationDto: PutNationDto
    ): GetNationWithChildrenDto {
        return nationService.putNation(putNationDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNation(
        @PathVariable id: Long
    ) {
        nationService.deleteNation(id)
    }

}
