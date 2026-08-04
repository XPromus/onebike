package com.xpromus.onebike_backend.rider.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class PostRiderDto(
    @field:Size(
        min = RiderValidation.FIRST_NAME_SIZE_MIN,
        max = RiderValidation.FIRST_NAME_SIZE_MAX,
        message = RiderValidation.FIRST_NAME_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = RiderValidation.FIRST_NAME_PATTERN_MESSAGE
    )
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val firstName: String,

    @field:Size(
        min = RiderValidation.LAST_NAME_SIZE_MIN,
        max = RiderValidation.LAST_NAME_SIZE_MAX,
        message = RiderValidation.LAST_NAME_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = RiderValidation.LAST_NAME_PATTERN_MESSAGE
    )
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val lastName: String,

    @field:NotNull(message = RiderValidation.DATE_OF_BIRTH_NOT_NULL_MESSAGE)
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val dateOfBirth: LocalDate,

    @field:NotNull(message = RiderValidation.NATION_ID_NOT_NULL_MESSAGE)
    @field:Positive(message = RiderValidation.NATION_ID_POSITIVE_MESSAGE)
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val nationId: Long,

    @field:Positive(message = RiderValidation.TEAM_ID_POSITIVE_MESSAGE)
    val teamId: Long?,
)
