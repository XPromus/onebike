package com.xpromus.onebike_backend.rider.dto

import java.time.LocalDate

data class RiderDescriptorDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate
)
