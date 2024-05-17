package com.gostock.data.usecase

import com.gostock.data.model.BaseUiModel
import com.gostock.data.util.ApiResult

interface RegisterUseCase {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String
    ): ApiResult<BaseUiModel>
}