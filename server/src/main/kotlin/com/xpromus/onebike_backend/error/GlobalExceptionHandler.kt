package com.xpromus.onebike_backend.error

import com.xpromus.onebike_backend.error.dto.ErrorResponse
import com.xpromus.onebike_backend.error.exception.BadRequestException
import com.xpromus.onebike_backend.error.mapper.toErrorResponse
import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLIntegrityConstraintViolationException

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

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(
        exception: DataIntegrityViolationException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val message = when (
            val rootCause = exception.rootCause
        ) {
            is SQLIntegrityConstraintViolationException -> {
                rootCause.message ?: "A constraint was violated."
            } else -> {
                "A constraint was violated."
            }
        }

        val errorStatus = HttpStatus.CONFLICT
        val errorResponse = ErrorResponse(
            status = errorStatus.value(),
            error = errorStatus.reasonPhrase,
            message = message,
            path = request.requestURI
        )

        return ResponseEntity(errorResponse, errorStatus)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = exception.bindingResult.fieldErrors.joinToString {
            "${it.field}: ${it.defaultMessage}"
        }

        val errorStatus = HttpStatus.BAD_REQUEST
        val errorResponse = ErrorResponse(
            status = errorStatus.value(),
            error = errorStatus.reasonPhrase,
            message = errors,
            path = request.requestURI
        )

        return ResponseEntity(errorResponse, errorStatus)
    }

}
