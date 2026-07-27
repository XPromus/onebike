package com.xpromus.onebike_backend.nation.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class NationFilter(
    @field:Positive(message = "Nation id must be a positive number")
    val id: Long?,
    @field:Size(max = 50, message = "Long name of a nation can not exceed 50 characters")
    @field:Pattern(regexp = "^[a-zA-ZöäüÖÄÜß]*$", message = "Long nation name filter contains invalid characters")
    val longName: String?,
    @field:Size(max = 5, message = "Short name of a nation can not exceed 5 characters")
    @field:Pattern(regexp = "^[a-zA-ZöäüÖÄÜß]*$", message = "Short nation name filter contains invalid characters")
    val shortName: String?,
)
