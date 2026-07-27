package com.xpromus.onebike_backend.cup.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import com.xpromus.onebike_backend.error.validator.ValidUrl
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class PostCupDto(
    @field:Size(
        min = CupValidation.CUP_NAME_MIN,
        max = CupValidation.CUP_NAME_MAX,
        message = CupValidation.CUP_NAME_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = CupValidation.CUP_NAME_PATTERN_MESSAGE
    )
    val cupName: String,

    @field:ValidUrl(
        message = CommonValidation.URL_MESSAGE,
        nullable = true
    )
    val url: String?,

    @field:NotNull(message = CupValidation.NATION_ID_NOT_NULL_MESSAGE)
    @field:Positive(message = CupValidation.NATION_ID_POSITIVE_MESSAGE)
    val nationId: Long
)
