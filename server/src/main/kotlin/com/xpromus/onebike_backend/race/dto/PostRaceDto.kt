package com.xpromus.onebike_backend.race.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.time.Instant
import java.time.LocalDate

data class PostRaceDto(
    @field:Size(
        min = RaceValidation.RACE_NAME_SIZE_MIN,
        max = RaceValidation.RACE_NAME_SIZE_MAX,
        message = RaceValidation.RACE_NAME_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = RaceValidation.RACE_NAME_PATTERN_MESSAGE
    )
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val raceName: String,

    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val lengthInKm: Float,

    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val raceDate: LocalDate,

    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val startTime: Instant,

    @field:NotNull(message = RaceValidation.NATION_ID_NOT_NULL_MESSAGE)
    @field:Positive(message = RaceValidation.NATION_ID_POSITIVE_MESSAGE)
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val nationId: Long,

    @field:Positive(message = RaceValidation.CUP_ID_POSITIVE_MESSAGE)
    val cupId: Long?,
)
