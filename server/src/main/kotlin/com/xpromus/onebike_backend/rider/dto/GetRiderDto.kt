package com.xpromus.onebike_backend.rider.dto

import java.time.LocalDate

data class GetRiderDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val placementIDs: List<Long>,
    val nationId: Long
)
