package com.gostock.data.usecase

import com.gostock.data.model.GetNotesUiModel
import com.gostock.data.repository.Repository
import com.gostock.data.util.ApiResult
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetNotesUseCase {
    override suspend fun invoke(): ApiResult<GetNotesUiModel> {
        return repository.getNotes()
    }
}