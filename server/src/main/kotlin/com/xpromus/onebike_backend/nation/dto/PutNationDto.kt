package com.xpromus.onebike_backend.nation.dto

data class PutNationDto(
    val id: Long?,
    val longName: String,
    val shortName: String,
    val flagEmoji: String,
)
