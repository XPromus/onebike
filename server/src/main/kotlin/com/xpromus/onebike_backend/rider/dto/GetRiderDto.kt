package com.xpromus.onebike_backend.rider.dto

data class GetRiderDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val placementIDs: List<Long>,
    val nationId: Long
)
