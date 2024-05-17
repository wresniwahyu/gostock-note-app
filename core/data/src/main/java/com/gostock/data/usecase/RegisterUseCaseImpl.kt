package com.gostock.data.usecase

import com.gostock.data.repository.Repository
import com.gostock.data.util.ApiResult
import com.gostock.network.model.RegisterBody
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: Repository
) : RegisterUseCase {
    override suspend fun invoke(name: String, email: String, password: String): ApiResult<Unit> {
        return repository.register(
            RegisterBody(
                name = name,
                email = email,
                password = password
            )
        )
    }
}