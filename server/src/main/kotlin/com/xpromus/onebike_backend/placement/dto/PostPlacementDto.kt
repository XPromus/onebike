package com.xpromus.onebike_backend.placement.dto

import com.xpromus.onebike_backend.error.validator.CommonValidation
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class PostPlacementDto(

    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val points: Int,

    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val finishTimeInSeconds: Int,

    @field:Size(
        min = PlacementValidation.FINISH_STATUS_SIZE_MIN,
        max = PlacementValidation.FINISH_STATUS_SIZE_MAX,
        message = PlacementValidation.FINISH_STATUS_SIZE_MESSAGE
    )
    @field:Pattern(
        regexp = CommonValidation.TEXT_REGEX,
        message = PlacementValidation.FINISH_STATUS_PATTERN_MESSAGE
    )
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val finishStatus: String,

    @field:NotNull(message = PlacementValidation.RIDER_ID_NOT_NULL_MESSAGE)
    @field:Positive(message = PlacementValidation.RIDER_ID_POSITIVE_MESSAGE)
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val riderId: Long,

    @field:NotNull(message = PlacementValidation.RACE_ID_NOT_NULL_MESSAGE)
    @field:Positive(message = PlacementValidation.RACE_ID_POSITIVE_MESSAGE)
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val raceId: Long,
)
