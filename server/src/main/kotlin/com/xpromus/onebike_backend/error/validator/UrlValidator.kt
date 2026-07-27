package com.xpromus.onebike_backend.error.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.net.URI

class UrlValidator : ConstraintValidator<ValidUrl, String> {

    private var nullable: Boolean = true

    override fun initialize(
        constraintAnnotation: ValidUrl
    ) {
        this.nullable = constraintAnnotation.nullable
    }

    override fun isValid(
        value: String?,
        context: ConstraintValidatorContext?
    ): Boolean {
        if (value == null) return nullable
        if (value.isBlank()) return nullable

        return try {
            URI(value).toURL()
            true
        } catch (e: Exception) {
            false
        }
    }
}
