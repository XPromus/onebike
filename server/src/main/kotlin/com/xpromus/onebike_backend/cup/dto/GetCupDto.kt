package com.xpromus.onebike_backend.cup.dto

data class GetCupDto(
    val id: Long,
    val cupName: String,
    val url: String?,
    val raceIds: List<Long>,
    val cupNationId: Long
)
