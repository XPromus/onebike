package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.dto.PutRiderDto
import com.xpromus.onebike_backend.util.SortDirection
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/riders")
class RiderController(
    private val riderService: RiderService
) {

    @GetMapping
    fun getRiders(
        @RequestParam(name = "sortBy", defaultValue = "lastName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetRiderDto> {
        return riderService.getRiders(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @GetMapping("/full")
    fun getRidersWithChildren(
        @RequestParam(name = "sortBy", defaultValue = "lastName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetRiderWithChildrenDto> {
        return riderService.getRidersWithChildren(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @PutMapping
    fun putRider(
        @RequestBody putRiderDto: PutRiderDto
    ): GetRiderDto {
        return riderService.putRider(putRiderDto)
    }

    @DeleteMapping("/{id}")
    fun deleteRider(
        @PathVariable id: Long
    ) {
        riderService.deleteRider(id)
    }

}
