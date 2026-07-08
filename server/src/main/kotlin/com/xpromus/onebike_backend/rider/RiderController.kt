package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.rider.dto.CreateRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.UpdateRiderDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @PostMapping
    fun createRider(
        createRiderDto: CreateRiderDto
    ): GetRiderDto {
        return riderService.createRider(createRiderDto)
    }

    @PutMapping
    fun updateRider(
        updateRiderDto: UpdateRiderDto
    ): GetRiderDto {
        return riderService.updateRider(updateRiderDto)
    }

    @DeleteMapping("/{id}")
    fun deleteRider(
        @PathVariable id: Long
    ) {
        riderService.deleteRider(id)
    }

}
