package com.gostock.data.usecase

import com.gostock.data.repository.Repository
import com.gostock.data.util.ApiResult
import com.gostock.data.model.BaseUiModel
import com.gostock.network.model.LoginBody
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: Repository
) : LoginUseCase {
    override suspend fun invoke(email: String, password: String): ApiResult<BaseUiModel> {
        return repository.login(
            LoginBody(
                email = email,
                password = password
            )
        )
    }
}