package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.team.dto.GetTeamDto
import com.xpromus.onebike_backend.team.dto.PutTeamDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/teams")
class TeamController(
    private val teamService: TeamService
) {

    @GetMapping
    fun getTeams(): List<GetTeamDto> {
        return teamService.getTeams()
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
