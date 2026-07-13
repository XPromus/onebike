package com.xpromus.onebike_backend.error

import com.xpromus.onebike_backend.error.dto.ErrorResponse
import com.xpromus.onebike_backend.error.excepion.BadRequestException
import com.xpromus.onebike_backend.error.mapper.toErrorResponse
import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalState(
        exception: IllegalStateException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        val error = exception.toErrorResponse(request, httpStatus)
        return ResponseEntity(error, httpStatus)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(
        exception: EntityNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val httpStatus = HttpStatus.NOT_FOUND
        val error = exception.toErrorResponse(request, httpStatus)
        return ResponseEntity(error, httpStatus)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(
        exception: BadRequestException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val httpStatus = HttpStatus.BAD_REQUEST
        val error = exception.toErrorResponse(request, httpStatus)
        return ResponseEntity(error, httpStatus)
    }

}
