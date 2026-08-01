package com.xpromus.onebike_backend.nation.dto

import io.swagger.v3.oas.annotations.media.Schema

data class GetNationDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val longName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val shortName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val flagEmoji: String,
)
