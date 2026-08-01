package com.xpromus.onebike_backend.cup.dto

import io.swagger.v3.oas.annotations.media.Schema

data class CupDescriptorDto(
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val id: Long,
    @field:Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val cupName: String,
    val url: String?
)
