package com.xpromus.onebike_backend.nation.dto

data class GetNationDto(
    val id: Long,
    val longName: String,
    val shortName: String,
    val flagEmoji: String,
)
