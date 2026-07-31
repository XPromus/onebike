package com.xpromus.onebike_backend.race.sort

enum class RaceSortField(
    val propertyName: String
) {
    RACE_NAME("raceName"),
    LENGTH_IN_KM("lengthInKm"),
    RACE_DATE("raceDate"),
    START_TIME("startTime"),
}
