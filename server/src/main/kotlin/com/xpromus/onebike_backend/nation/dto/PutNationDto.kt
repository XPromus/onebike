package com.xpromus.onebike_backend.nation.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class PutNationDto(
    @field:Size(
        min = NationValidation.LONG_NAME_SIZE_MIN,
        max = NationValidation.LONG_NAME_SIZE_MAX,
        message = NationValidation.LONG_NAME_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = NationValidation.LONG_NAME_PATTERN_MESSAGE
    )
    val longName: String,

    @field:Size(
        min = NationValidation.SHORT_NAME_SIZE_MIN,
        max = NationValidation.SHORT_NAME_SIZE_MAX,
        message = NationValidation.SHORT_NAME_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = NationValidation.SHORT_NAME_PATTERN_MESSAGE
    )
    val shortName: String,

    @field:NotBlank(
        message = NationValidation.FLAG_EMOJI_REQUIRED
    )
    val flagEmoji: String,
)
