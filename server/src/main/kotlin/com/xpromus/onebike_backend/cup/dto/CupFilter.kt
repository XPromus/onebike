package com.xpromus.onebike_backend.cup.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class CupFilter(
    @field:Positive(
        message = CommonValidation.ID_POSITIVE_MESSAGE
    )
    val id: Long?,

    @field:Size(
        max = CupValidation.CUP_NAME_MAX,
        message = CupValidation.CUP_NAME_FILTER_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = CupValidation.CUP_NAME_FILTER_PATTERN_MESSAGE
    )
    val cupName: String?,
)
