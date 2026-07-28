package com.xpromus.onebike_backend.rider.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class RiderFilter(
    @field:Positive(
        message = CommonValidation.ID_POSITIVE_MESSAGE
    )
    val id: Long?,

    @field:Size(
        max = RiderValidation.FIRST_NAME_SIZE_MAX,
        message = RiderValidation.FIRST_NAME_FILTER_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = RiderValidation.FIRST_NAME_FILTER_PATTERN_MESSAGE
    )
    val firstName: String?,

    @field:Size(
        max = RiderValidation.LAST_NAME_SIZE_MAX,
        message = RiderValidation.LAST_NAME_FILTER_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = RiderValidation.LAST_NAME_FILTER_PATTERN_MESSAGE
    )
    val lastName: String?,

    val dateOfBirth: LocalDate?,
    val minDateOfBirth: LocalDate?,
    val maxDateOfBirth: LocalDate?,
)
