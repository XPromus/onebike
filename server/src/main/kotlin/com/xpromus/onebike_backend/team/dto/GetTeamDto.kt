package com.xpromus.onebike_backend.team.dto

data class GetTeamDto(
    val id: Long,
    val teamName: String,
    val shortName: String,
    val teamDescription: String,
    val nationalityId: Long,
    val riderIds: List<Long>
)
