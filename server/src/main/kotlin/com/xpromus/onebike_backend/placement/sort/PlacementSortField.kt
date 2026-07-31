package com.xpromus.onebike_backend.placement.sort

enum class PlacementSortField(
    val propertyName: String
) {
    POINTS("points"),
    FINISH_TIME_IN_SECONDS("finishTimeInSeconds"),
    FINISH_STATUS("finishStatus"),
}
