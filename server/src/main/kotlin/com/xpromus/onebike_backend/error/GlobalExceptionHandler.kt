package com.xpromus.onebike_backend.error

import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(
        exception: EntityNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val httpStatus = HttpStatus.NOT_FOUND
        val error = ErrorResponse(
            status = httpStatus.value(),
            error = httpStatus.reasonPhrase,
            message = exception.message,
            path = request.requestURI
        )
        return ResponseEntity(error, httpStatus)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(
        exception: BadRequestException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val httpStatus = HttpStatus.BAD_REQUEST
        val error = ErrorResponse(
            status = httpStatus.value(),
            error = httpStatus.reasonPhrase,
            message = exception.message,
            path = request.requestURI
        )
        return ResponseEntity(error, httpStatus)
    }

}
