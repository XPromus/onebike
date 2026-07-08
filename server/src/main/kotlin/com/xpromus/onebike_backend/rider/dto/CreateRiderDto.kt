package com.xpromus.onebike_backend.rider.dto

data class CreateRiderDto(
    val firstName: String,
    val lastName: String,
    val nationId: Long
)
