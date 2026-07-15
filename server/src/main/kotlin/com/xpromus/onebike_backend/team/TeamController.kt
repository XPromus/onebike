package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.team.dto.GetTeamDto
import com.xpromus.onebike_backend.team.dto.PutTeamDto
import com.xpromus.onebike_backend.util.SortDirection
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/teams")
class TeamController(
    private val teamService: TeamService
) {

    @GetMapping
    fun getTeams(
        @RequestParam(name = "sortBy", defaultValue = "teamName") sortBy: String,
        @RequestParam(name = "sortDir", defaultValue = "ASCENDING") sortDirection: SortDirection
    ): List<GetTeamDto> {
        return teamService.getTeams(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }

    @PutMapping
    fun putTeam(
        putTeamDto: PutTeamDto
    ): GetTeamDto {
        return teamService.putTeam(putTeamDto)
    }

    @DeleteMapping("/{id}")
    fun deleteTeam(
        @PathVariable id: Long
    ) {
        teamService.deleteTeam(id)
    }

}
