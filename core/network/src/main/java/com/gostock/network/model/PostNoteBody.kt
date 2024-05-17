package com.gostock.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostNoteBody(
    @Json(name = "title")
    val title: String,
    @Json(name = "note")
    val note: String,
)
