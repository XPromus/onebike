package com.xpromus.onebike_backend.error.mapper

import com.xpromus.onebike_backend.error.dto.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus

fun Exception.toErrorResponse(
    request: HttpServletRequest,
    httpStatus: HttpStatus
): ErrorResponse {
    return ErrorResponse(
        status = httpStatus.value(),
        error = httpStatus.reasonPhrase,
        message = message,
        path = request.requestURI
    )
}
