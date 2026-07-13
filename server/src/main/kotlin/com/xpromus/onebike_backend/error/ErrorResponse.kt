package com.xpromus.onebike_backend.error

import kotlin.time.Clock
import kotlin.time.Instant

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String?,
    val path: String,
    val timestamp: Instant = Clock.System.now(),
)
