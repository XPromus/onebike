package com.xpromus.onebike_backend.cup.dto

import io.swagger.v3.oas.annotations.media.Schema

data class GetCupDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val cupName: String,
    val url: String?,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val raceIds: List<Long>,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val nationId: Long,
)
