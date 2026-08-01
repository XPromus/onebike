package com.xpromus.onebike_backend.team.dto

import io.swagger.v3.oas.annotations.media.Schema

data class TeamDescriptorDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val teamName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val shortName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val teamDescription: String,
)
