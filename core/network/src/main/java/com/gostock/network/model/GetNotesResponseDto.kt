package com.gostock.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetNotesResponseDto(
    @Json(name = "success")
    val success: Boolean? = null,
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "data")
    val data: List<NoteDataResponseDto>? = null
)

@JsonClass(generateAdapter = true)
data class NoteDataResponseDto(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "note")
    val note: String? = null,
    @Json(name = "creator")
    val creator: String? = null,
    @Json(name = "created_at")
    val createdAt: String? = null,
    @Json(name = "updated_at")
    val updatedAt: String? = null,
)