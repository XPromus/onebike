package com.xpromus.onebike_backend.team.dto

data class PutTeamDto(
    val id: Long?,
    val teamName: String,
    val shortName: String,
    val teamDescription: String,
    val nationId: Long
)
