package com.xpromus.onebike_backend.team.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class PutTeamDto(
    @field:Size(
        min = TeamValidation.TEAM_NAME_SIZE_MIN,
        max = TeamValidation.TEAM_NAME_SIZE_MAX,
        message = TeamValidation.TEAM_NAME_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = TeamValidation.TEAM_NAME_PATTERN_MESSAGE
    )
    val teamName: String,

    @field:Size(
        min = TeamValidation.SHORT_NAME_SIZE_MIN,
        max = TeamValidation.SHORT_NAME_SIZE_MAX,
        message = TeamValidation.SHORT_NAME_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = TeamValidation.SHORT_NAME_PATTERN_MESSAGE
    )
    val shortName: String,

    @field:Size(
        max = TeamValidation.TEAM_DESCRIPTION_SIZE_MAX,
        message = TeamValidation.TEAM_DESCRIPTION_SIZE_MESSAGE
    )
    val teamDescription: String,

    @field:NotNull(message = TeamValidation.NATION_ID_NOT_NULL_MESSAGE)
    @field:Positive(message = TeamValidation.NATION_ID_POSITIVE_MESSAGE)
    val nationId: Long
)
