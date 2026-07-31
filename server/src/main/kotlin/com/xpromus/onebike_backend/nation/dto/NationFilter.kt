package com.xpromus.onebike_backend.nation.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class NationFilter(
    @field:Positive(
        message = CommonValidation.ID_POSITIVE_MESSAGE
    )
    val id: Long?,

    @field:Size(
        max = NationValidation.LONG_NAME_SIZE_MAX,
        message = NationValidation.LONG_NAME_FILTER_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = NationValidation.LONG_NAME_FILTER_PATTERN_MESSAGE
    )
    val longName: String?,

    @field:Size(
        max = NationValidation.SHORT_NAME_SIZE_MAX,
        message = NationValidation.SHORT_NAME_FILTER_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = NationValidation.SHORT_NAME_FILTER_PATTERN_MESSAGE
    )
    val shortName: String?,
)
