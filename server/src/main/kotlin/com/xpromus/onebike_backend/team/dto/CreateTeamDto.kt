package com.xpromus.onebike_backend.team.dto

data class CreateTeamDto(
    val teamName: String,
    val shortName: String,
    val teamDescription: String,
    val nationId: Long,
)
