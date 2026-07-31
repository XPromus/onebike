package com.xpromus.onebike_backend.placement.dto

object PlacementValidation {
    const val FINISH_STATUS_SIZE_MIN = 1
    const val FINISH_STATUS_SIZE_MAX = 50

    const val FINISH_STATUS_SIZE_MESSAGE = "Finish status can not exceed $FINISH_STATUS_SIZE_MAX characters"
    const val FINISH_STATUS_PATTERN_MESSAGE = "Finish status contains invalid characters"
    const val FINISH_STATUS_FILTER_SIZE_MESSAGE = "Finish status filter can not exceed $FINISH_STATUS_SIZE_MAX characters"
    const val FINISH_STATUS_FILTER_PATTERN_MESSAGE = "Finish status filter contains invalid characters"

    const val RIDER_ID_NOT_NULL_MESSAGE = "Placement needs a rider id"
    const val RIDER_ID_POSITIVE_MESSAGE = "Rider id in placement must be positive"
    const val RACE_ID_NOT_NULL_MESSAGE = "Placement needs a race id"
    const val RACE_ID_POSITIVE_MESSAGE = "Race id in placement must be positive"
}
