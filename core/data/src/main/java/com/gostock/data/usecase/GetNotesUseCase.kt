package com.gostock.data.usecase

import com.gostock.data.util.ApiResult
import com.gostock.data.model.GetNotesUiModel

interface GetNotesUseCase {
    suspend operator fun invoke(): ApiResult<GetNotesUiModel>

}