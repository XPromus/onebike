package com.xpromus.onebike_backend.team

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long> {
    fun findTeamsByTeamNameLikeOrShortNameLike(teamName: String, shortName: String): MutableList<Team>
}
