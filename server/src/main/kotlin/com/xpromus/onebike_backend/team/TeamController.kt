package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.team.dto.*
import com.xpromus.onebike_backend.team.sort.TeamSortField
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
@RequestMapping("/api/v1/teams")
class TeamController(
    private val teamService: TeamService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getTeams(
        @Valid @ModelAttribute filter: TeamFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "TEAM_NAME") sortBy: TeamSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): ResponseEntity<Page<GetTeamDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val teams: Page<GetTeamDto> = teamService.findTeams(
            filter = filter,
            pageable = pageable,
        )

        return ResponseEntity.ok(teams)
    }

    @GetMapping("/full")
    @ResponseStatus(HttpStatus.OK)
    fun getTeamsWithChildren(
        @Valid @ModelAttribute filter: TeamFilter,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "TEAM_NAME") sortBy: TeamSortField,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): ResponseEntity<Page<GetTeamWithChildrenDto>> {
        val sort = Sort.by(sortDirection.toSortDir(), sortBy.propertyName)
        val boundedSize = pageSize.coerceIn(0, 100)
        val pageable = PageRequest.of(page, boundedSize, sort)

        val teams: Page<GetTeamWithChildrenDto> = teamService.findTeamsWithChildren(
            filter = filter,
            pageable = pageable
        )

        return ResponseEntity.ok(teams)
    }

    @PutMapping("/{id}")
    fun putTeam(
        @PathVariable id: Long,
        @Valid @RequestBody putTeamDto: PutTeamDto
    ): ResponseEntity<GetTeamDto> {
        val (body, wasCreated) = teamService.putTeam(id, putTeamDto)
        val status = if (wasCreated) HttpStatus.CREATED else HttpStatus.OK
        return ResponseEntity(body, status)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTeam(
        @Valid @RequestBody postTeamDto: PostTeamDto
    ): ResponseEntity<GetTeamDto> {
        val savedTeam = teamService.createTeam(postTeamDto)
        val location = URI.create("/api/v1/teams/${savedTeam.id}")
        return ResponseEntity.created(location).body(savedTeam)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTeam(
        @PathVariable id: Long
    ) {
        teamService.deleteTeam(id)
    }

}
