package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.CupFilter
import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.dto.PostCupDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.cup.sort.CupSortField
import com.xpromus.onebike_backend.util.SortDirection
import com.xpromus.onebike_backend.util.toSortDir
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import java.net.URI

@RestController
@RequestMapping("/api/v1/cups")
class CupController(
    private val cupService: CupService
) {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun getCups(
        @Valid @ModelAttribute filter: CupFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "CUP_NAME") sortBy: CupSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): ResponseEntity<Page<GetCupDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val cups: Page<GetCupDto> = cupService.findCups(
            filter = filter,
            pageable = pageable
        )
        return ResponseEntity.ok(cups)
    }

    @GetMapping("/full")
    @ResponseStatus(value = HttpStatus.OK)
    fun getCupsWithChildren(
        @Valid @ModelAttribute filter: CupFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "CUP_NAME") sortBy: CupSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): ResponseEntity<Page<GetCupWithChildrenDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val cups: Page<GetCupWithChildrenDto> = cupService.findCupsWithChildren(
            filter = filter,
            pageable = pageable
        )
        return ResponseEntity.ok(cups)
    }

    @GetMapping("/nation/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun getCupsInNation(
        @PathVariable id: Long,
        @Valid @ModelAttribute filter: CupFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "cupName") sortBy: CupSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): ResponseEntity<Page<GetCupDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val cups: Page<GetCupDto> = cupService.findCupsInNation(
            id = id,
            filter = filter,
            pageable = pageable
        )
        return ResponseEntity.ok(cups)
    }

    @PutMapping("/{id}")
    fun putCup(
        @PathVariable id: Long,
        @Valid @RequestBody putCupDto: PutCupDto
    ): ResponseEntity<GetCupDto> {
        val (body, wasCreated) = cupService.putCup(id, putCupDto)
        val status = if (wasCreated) HttpStatus.CREATED else HttpStatus.OK
        return ResponseEntity(body, status)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCup(
        @Valid @RequestBody postCupDto: PostCupDto
    ): ResponseEntity<GetCupDto> {
        val savedCup = cupService.createCup(postCupDto)
        val location = URI.create("/api/v1/cups/${savedCup.id}")
        return ResponseEntity.created(location).body(savedCup)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun deleteCup(
        @PathVariable id: Long
    ) {
        cupService.deleteCup(id)
    }

}
