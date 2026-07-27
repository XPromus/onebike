package com.xpromus.onebike_backend.nation.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class PostNationDto(
    @field:Size(min = 1, max = 50, message = "Long name of a nation must be between 1 and 50 characters")
    @field:Pattern(regexp = "^[a-zA-ZöäüÖÄÜß]*$", message = "Long nation name filter contains invalid characters")
    val longName: String,
    @field:Size(min = 1, max = 5, message = "Short name of a nation must be between 1 and 5 characters")
    @field:Pattern(regexp = "^[a-zA-ZöäüÖÄÜß]*$", message = "Short nation name filter contains invalid characters")
    val shortName: String,
    @field:NotBlank(message = "Nation must have a flag emoji")
    val flagEmoji: String,
)
