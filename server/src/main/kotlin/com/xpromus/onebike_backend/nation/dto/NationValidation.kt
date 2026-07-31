package com.xpromus.onebike_backend.nation.dto

object NationValidation {
    const val LONG_NAME_SIZE_MIN = 1
    const val LONG_NAME_SIZE_MAX = 50
    const val SHORT_NAME_SIZE_MIN = 1
    const val SHORT_NAME_SIZE_MAX = 5

    const val LONG_NAME_SIZE_MESSAGE = "Long name of a nation can not exceed $LONG_NAME_SIZE_MAX characters"
    const val LONG_NAME_PATTERN_MESSAGE = "Long nation name contains invalid characters"

    const val SHORT_NAME_SIZE_MESSAGE = "Short name of a nation must be between $SHORT_NAME_SIZE_MIN and $SHORT_NAME_SIZE_MAX characters"
    const val SHORT_NAME_PATTERN_MESSAGE = "Short nation name contains invalid characters"

    const val FLAG_EMOJI_REQUIRED = "Nation must have a flag emoji"

    const val LONG_NAME_FILTER_SIZE_MESSAGE = "Long name filter can not exceed $LONG_NAME_SIZE_MAX characters"
    const val LONG_NAME_FILTER_PATTERN_MESSAGE = "Long name filter contains invalid characters"
    const val SHORT_NAME_FILTER_SIZE_MESSAGE = "Short name filter can not exceed $SHORT_NAME_SIZE_MAX characters"
    const val SHORT_NAME_FILTER_PATTERN_MESSAGE = "Short name filter contains invalid characters"
}
