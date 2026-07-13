package com.xpromus.onebike_backend.nation.dto

data class GetNationWithChildrenDto(
    val id: Long,
    val longName: String,
    val shortName: String,
    val flagEmoji: String,
    val riderIds: List<Long>,
    val cupIds: List<Long>,
    val raceIds: List<Long>,
)
