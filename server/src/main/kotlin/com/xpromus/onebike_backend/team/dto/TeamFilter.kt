package com.xpromus.onebike_backend.team.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class TeamFilter(
    @field:Positive(
        message = CommonValidation.ID_POSITIVE_MESSAGE
    )
    val id: Long?,

    @field:Size(
        max = TeamValidation.TEAM_NAME_SIZE_MAX,
        message = TeamValidation.TEAM_NAME_FILTER_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = TeamValidation.TEAM_NAME_FILTER_PATTERN_MESSAGE
    )
    val teamName: String?,

    @field:Size(
        max = TeamValidation.SHORT_NAME_SIZE_MAX,
        message = TeamValidation.SHORT_NAME_FILTER_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = TeamValidation.SHORT_NAME_FILTER_PATTERN_MESSAGE
    )
    val shortName: String?,
)
