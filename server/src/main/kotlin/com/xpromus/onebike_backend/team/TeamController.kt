package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.team.dto.GetTeamDto
import com.xpromus.onebike_backend.team.dto.GetTeamWithChildrenDto
import com.xpromus.onebike_backend.team.dto.PutTeamDto
import com.xpromus.onebike_backend.util.SortDirection
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/teams")
class TeamController(
    private val teamService: TeamService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getTeams(
        @RequestParam(name = "sortBy", defaultValue = "teamName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetTeamDto> {
        return teamService.getTeams(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @GetMapping("/full")
    @ResponseStatus(HttpStatus.OK)
    fun getTeamsWithChildren(
        @RequestParam(name = "sortBy", defaultValue = "teamName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetTeamWithChildrenDto> {
        return teamService.getTeamsWithChildren(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun putTeam(
        putTeamDto: PutTeamDto
    ): GetTeamDto {
        return teamService.putTeam(putTeamDto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTeam(
        @PathVariable id: Long
    ) {
        teamService.deleteTeam(id)
    }

}
