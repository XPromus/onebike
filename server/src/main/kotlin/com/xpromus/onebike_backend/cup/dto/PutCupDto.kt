package com.xpromus.onebike_backend.cup.dto

data class PutCupDto(
    val id: Long?,
    val cupName: String,
    val url: String?,
    val nationId: Long
)
