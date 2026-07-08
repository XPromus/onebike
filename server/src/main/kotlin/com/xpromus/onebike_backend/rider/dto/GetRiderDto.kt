package com.xpromus.onebike_backend.rider.dto

data class GetRiderDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val cupPlacementIDs: List<Long>,
    val nationId: Long
)
