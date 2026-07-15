package com.xpromus.onebike_backend.search.dto

import com.xpromus.onebike_backend.util.SortDirection

data class PostSearchDto(
    val searchString: String,
    val ridersSortBy: String,
    val cupsSortBy: String,
    val racesSortBy: String,
    val teamsSortBy: String,
    val sortDirection: SortDirection
)
