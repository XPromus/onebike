package com.xpromus.onebike_backend.team

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/teams")
class TeamController(
    private val teamService: TeamService
) {

}
