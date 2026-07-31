package com.xpromus.onebike_backend.race.dto

object RaceValidation {
    const val RACE_NAME_SIZE_MIN = 1
    const val RACE_NAME_SIZE_MAX = 200

    const val RACE_NAME_SIZE_MESSAGE = "Race name can not exceed $RACE_NAME_SIZE_MAX characters"
    const val RACE_NAME_PATTERN_MESSAGE = "Race name contains invalid characters"
    const val RACE_NAME_FILTER_SIZE_MESSAGE = "Race name filter can not exceed $RACE_NAME_SIZE_MAX characters"
    const val RACE_NAME_FILTER_PATTERN_MESSAGE = "Race name filter contains invalid characters"

    const val NATION_ID_NOT_NULL_MESSAGE = "Race needs a nation id"
    const val NATION_ID_POSITIVE_MESSAGE = "Nation id in race must be positive"
    const val CUP_ID_POSITIVE_MESSAGE = "Cup id in race must be positive"
}
