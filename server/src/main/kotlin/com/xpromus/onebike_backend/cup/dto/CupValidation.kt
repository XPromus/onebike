package com.xpromus.onebike_backend.cup.dto

object CupValidation {
    const val CUP_NAME_MIN = 1
    const val CUP_NAME_MAX = 200

    const val CUP_NAME_SIZE_MESSAGE = "cupName can not exceed $CUP_NAME_MAX characters"
    const val CUP_NAME_PATTERN_MESSAGE = "cupName contains invalid characters"
    const val CUP_NAME_FILTER_SIZE_MESSAGE = "cupName filter can not exceed $CUP_NAME_MAX characters"
    const val CUP_NAME_FILTER_PATTERN_MESSAGE = "cupName filter contains invalid characters"

    const val NATION_ID_NOT_NULL_MESSAGE = "Cup needs a nation id"
    const val NATION_ID_POSITIVE_MESSAGE = "Nation id in cup must be positive"
}
