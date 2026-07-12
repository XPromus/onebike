package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.PutRiderDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/riders")
class RiderController(
    private val riderService: RiderService
) {

    @GetMapping
    fun getRiders(): List<GetRiderDto> {
        return riderService.getRiders()
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
