package com.gostock.data.usecase

import com.gostock.data.repository.Repository
import javax.inject.Inject

class LogoutUseCaseImpl @Inject constructor(
    private val repository: Repository
) : LogoutUseCase {
    override suspend fun invoke() {
        return repository.clearSession()
    }
}