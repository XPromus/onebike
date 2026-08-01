package com.xpromus.onebike_backend.rider.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class RiderDescriptorDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val firstName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val lastName: String,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val dateOfBirth: LocalDate
)
