package com.xpromus.onebike_backend.rider.dto

import java.time.LocalDate

data class PutRiderDto(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val nationId: Long
)
