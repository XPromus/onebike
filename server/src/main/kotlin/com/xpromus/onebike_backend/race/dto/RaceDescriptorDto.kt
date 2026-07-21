package com.xpromus.onebike_backend.race.dto

import java.time.Instant
import java.time.LocalDate

data class RaceDescriptorDto(
    val id: Long,
    val raceName: String,
    val lengthInKm: Float,
    val raceDate: LocalDate,
    val startTime: Instant,
)
