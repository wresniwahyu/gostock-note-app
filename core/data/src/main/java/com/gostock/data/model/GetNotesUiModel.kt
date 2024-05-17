package com.gostock.data.model

data class GetNotesUiModel(
    val success: Boolean,
    val message: String,
    val data: List<NoteDataUiModel>
)

data class NoteDataUiModel(
    val id: String,
    val title: String,
    val note: String,
    val creator: String,
    val createdAt: String,
    val updatedAt: String,
)