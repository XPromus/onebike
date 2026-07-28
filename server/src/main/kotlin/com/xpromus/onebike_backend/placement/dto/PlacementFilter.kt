package com.xpromus.onebike_backend.placement.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class PlacementFilter(
    @field:Positive(
        message = CommonValidation.ID_POSITIVE_MESSAGE
    )
    val id: Long?,

    val points: Int?,
    val minPoints: Int?,
    val maxPoints: Int?,

    val finishTimeInSeconds: Int?,
    val minFinishTimeInSeconds: Int?,
    val maxFinishTimeInSeconds: Int?,

    @field:Size(
        max = PlacementValidation.FINISH_STATUS_SIZE_MAX,
        message = PlacementValidation.FINISH_STATUS_FILTER_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = PlacementValidation.FINISH_STATUS_FILTER_PATTERN_MESSAGE
    )
    val finishStatus: String?,
)
