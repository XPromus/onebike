package com.xpromus.onebike_backend.rider.dto

object RiderValidation {
    const val FIRST_NAME_SIZE_MIN = 1
    const val FIRST_NAME_SIZE_MAX = 50
    const val LAST_NAME_SIZE_MIN = 1
    const val LAST_NAME_SIZE_MAX = 50

    const val FIRST_NAME_SIZE_MESSAGE = "First name can not exceed $FIRST_NAME_SIZE_MAX characters"
    const val FIRST_NAME_PATTERN_MESSAGE = "First name contains invalid characters"
    const val FIRST_NAME_FILTER_SIZE_MESSAGE = "First name filter can not exceed $FIRST_NAME_SIZE_MAX characters"
    const val FIRST_NAME_FILTER_PATTERN_MESSAGE = "First name filter contains invalid characters"

    const val LAST_NAME_SIZE_MESSAGE = "Last name can not exceed $LAST_NAME_SIZE_MAX characters"
    const val LAST_NAME_PATTERN_MESSAGE = "Last name contains invalid characters"
    const val LAST_NAME_FILTER_SIZE_MESSAGE = "Last name filter can not exceed $LAST_NAME_SIZE_MAX characters"
    const val LAST_NAME_FILTER_PATTERN_MESSAGE = "Last name filter contains invalid characters"

    const val DATE_OF_BIRTH_NOT_NULL_MESSAGE = "Rider must have a date of birth"
    const val NATION_ID_NOT_NULL_MESSAGE = "Rider needs a nation id"
    const val NATION_ID_POSITIVE_MESSAGE = "Nation id in rider must be positive"
    const val TEAM_ID_POSITIVE_MESSAGE = "Team id in rider must be positive"
}
