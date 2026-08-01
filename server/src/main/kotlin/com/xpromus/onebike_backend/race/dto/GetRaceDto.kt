package com.xpromus.onebike_backend.race.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant
import java.time.LocalDate

data class GetRaceDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val raceName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val lengthInKm: Float,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val raceDate: LocalDate,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val startTime: Instant,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val nationId: Long,
    val cupId: Long?,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val placementIds: List<Long>,
)
