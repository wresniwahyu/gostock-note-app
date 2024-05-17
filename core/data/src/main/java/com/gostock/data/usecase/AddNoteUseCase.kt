package com.gostock.data.usecase

import com.gostock.data.util.ApiResult

interface AddNoteUseCase {
    suspend operator fun invoke(
        title: String,
        note: String
    ): ApiResult<Unit>

}