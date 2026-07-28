package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.dto.PostRiderDto
import com.xpromus.onebike_backend.rider.dto.PutRiderDto
import com.xpromus.onebike_backend.rider.dto.RiderFilter
import com.xpromus.onebike_backend.rider.sort.RiderSortField
import com.xpromus.onebike_backend.util.SortDirection
import com.xpromus.onebike_backend.util.toSortDir
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/riders")
class RiderController(
    private val riderService: RiderService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getRiders(
        @Valid @ModelAttribute filter: RiderFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "LAST_NAME") sortBy: RiderSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): ResponseEntity<Page<GetRiderDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val riders: Page<GetRiderDto> = riderService.findRiders(
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(riders)
    }

    @GetMapping("/full")
    @ResponseStatus(HttpStatus.OK)
    fun getRidersWithChildren(
        @Valid @ModelAttribute filter: RiderFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "LAST_NAME") sortBy: RiderSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): ResponseEntity<Page<GetRiderWithChildrenDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val riders: Page<GetRiderWithChildrenDto> = riderService.findRidersWithChildren(
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(riders)
    }

    @PutMapping("/{id}")
    fun putRider(
        @PathVariable id: Long,
        @Valid @RequestBody putRiderDto: PutRiderDto
    ): ResponseEntity<GetRiderDto> {
        val (body, wasCreated) = riderService.putRider(id, putRiderDto)
        val status = if (wasCreated) HttpStatus.CREATED else HttpStatus.OK
        return ResponseEntity(body, status)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRider(
        @Valid @RequestBody postRiderDto: PostRiderDto
    ): ResponseEntity<GetRiderDto> {
        val savedRider = riderService.createRider(postRiderDto)
        val location = URI.create("/api/v1/riders/${savedRider.id}")
        return ResponseEntity.created(location).body(savedRider)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRider(
        @PathVariable id: Long
    ) {
        riderService.deleteRider(id)
    }

}
