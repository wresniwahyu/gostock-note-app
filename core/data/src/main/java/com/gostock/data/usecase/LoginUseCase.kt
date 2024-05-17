package com.gostock.data.usecase

import com.gostock.data.util.ApiResult
import com.gostock.data.model.BaseUiModel

interface LoginUseCase {
    suspend operator fun invoke(
        email: String,
        password: String
    ): ApiResult<BaseUiModel>
}