package com.gostock.data.usecase

import com.gostock.data.util.ApiResult

interface DeleteNoteUseCase {
    suspend operator fun invoke(
        id: String
    ): ApiResult<Unit>

}