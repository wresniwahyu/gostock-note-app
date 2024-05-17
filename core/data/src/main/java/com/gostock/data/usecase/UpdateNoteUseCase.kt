package com.gostock.data.usecase

import com.gostock.data.util.ApiResult

interface UpdateNoteUseCase {
    suspend operator fun invoke(
        id: String,
        title: String,
        note: String
    ): ApiResult<Unit>

}