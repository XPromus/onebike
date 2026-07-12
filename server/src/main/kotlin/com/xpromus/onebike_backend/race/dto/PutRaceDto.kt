package com.xpromus.onebike_backend.race.dto

data class PutRaceDto(
    val id: Long?,
    val raceName: String,
    val lengthInKm: Float,
    val nationId: Long,
    val cupId: Long?,
)
