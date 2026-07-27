package com.xpromus.onebike_backend.error.validator

object CommonValidation {
    const val TEXT_REGEX = "^[a-zA-ZöäüÖÄÜß]*$"

    const val URL_MESSAGE = "Must be a valid URL"
    const val ID_POSITIVE_MESSAGE = "Id must be a positive number"
}
