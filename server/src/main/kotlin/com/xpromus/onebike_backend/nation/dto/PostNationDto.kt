package com.xpromus.onebike_backend.nation.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class PostNationDto(
    @field:Size(
        min = NationValidation.LONG_NAME_SIZE_MIN,
        max = NationValidation.LONG_NAME_SIZE_MAX,
        message = NationValidation.LONG_NAME_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = NationValidation.LONG_NAME_PATTERN_MESSAGE
    )
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
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
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val shortName: String,

    @field:NotBlank(
        message = NationValidation.FLAG_EMOJI_REQUIRED
    )
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val flagEmoji: String,
)
