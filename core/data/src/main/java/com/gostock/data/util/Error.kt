package com.gostock.data.util

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Error(
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "errors")
    val errors: List<FieldError>? = null,
)

@JsonClass(generateAdapter = true)
data class FieldError(
    @Json(name = "field")
    val field: String? = null,
    @Json(name = "message")
    val message: List<String>? = null,
)