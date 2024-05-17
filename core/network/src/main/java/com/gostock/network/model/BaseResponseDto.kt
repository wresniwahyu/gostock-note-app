package com.gostock.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponseDto(
    @Json(name = "success")
    val success: Boolean? = null,
    @Json(name = "message")
    val message: String? = null
)
