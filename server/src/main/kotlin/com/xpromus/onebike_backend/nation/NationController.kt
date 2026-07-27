package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.nation.dto.*
import com.xpromus.onebike_backend.nation.sort.NationSortField
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
@RequestMapping("/api/v1/nations")
class NationController(
    private val nationService: NationService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getNations(
        @Valid @ModelAttribute filter: NationFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "LONG_NAME") sortBy: NationSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): ResponseEntity<Page<GetNationDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val nations: Page<GetNationDto> = nationService.findNations(
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(nations)
    }

    @GetMapping("/full")
    @ResponseStatus(HttpStatus.OK)
    fun getNationsWithChildren(
        @Valid @ModelAttribute filter: NationFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "LONG_NAME") sortBy: NationSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): ResponseEntity<Page<GetNationWithChildrenDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val nations: Page<GetNationWithChildrenDto> = nationService.findNationsWithChildren(
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(nations)
    }

    @PutMapping("/{id}")
    fun putNation(
        @PathVariable id: Long,
        @Valid @RequestBody putNationDto: PutNationDto
    ): ResponseEntity<GetNationDto> {
        val (body, wasCreated) = nationService.putNation(id, putNationDto)
        val status = if (wasCreated) HttpStatus.CREATED else HttpStatus.OK
        return ResponseEntity(body, status)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNation(
        @Valid @RequestBody postNationDto: PostNationDto
    ): ResponseEntity<GetNationDto> {
        val savedNation = nationService.createNation(postNationDto)
        val location = URI.create("/nations/${savedNation.id}")
        return ResponseEntity.created(location).body(savedNation)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNation(
        @PathVariable id: Long
    ) {
        nationService.deleteNation(id)
    }

}
