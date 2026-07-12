package com.xpromus.onebike_backend.race.dto

data class GetRaceDto(
    val id: Long,
    val raceName: String,
    val lengthInKm: Float,
    val countryId: Long,
    val cupId: Long?,
    val placementIds: List<Long>,
)
