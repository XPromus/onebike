package com.xpromus.onebike_backend.race.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.time.Instant
import java.time.LocalDate

data class RaceFilter(
    @field:Positive(
        message = CommonValidation.ID_POSITIVE_MESSAGE
    )
    val id: Long?,

    @field:Size(
        max = RaceValidation.RACE_NAME_SIZE_MAX,
        message = RaceValidation.RACE_NAME_FILTER_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = RaceValidation.RACE_NAME_FILTER_PATTERN_MESSAGE
    )
    val raceName: String?,

    val lengthInKm: Float?,
    val minLengthInKm: Float?,
    val maxLengthInKm: Float?,

    val raceDate: LocalDate?,
    val minRaceDate: LocalDate?,
    val maxRaceDate: LocalDate?,

    val startTime: Instant?,
    val minStartTime: Instant?,
    val maxStartTime: Instant?,
)
