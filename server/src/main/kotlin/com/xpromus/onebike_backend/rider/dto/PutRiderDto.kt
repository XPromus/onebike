package com.xpromus.onebike_backend.rider.dto

data class PutRiderDto(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val nationId: Long
)
