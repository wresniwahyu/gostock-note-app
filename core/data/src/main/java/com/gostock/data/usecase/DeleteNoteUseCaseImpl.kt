package com.gostock.data.usecase

import com.gostock.data.repository.Repository
import com.gostock.data.util.ApiResult
import com.gostock.network.model.PostNoteBody
import javax.inject.Inject

class DeleteNoteUseCaseImpl @Inject constructor(
    private val repository: Repository
) : DeleteNoteUseCase {
    override suspend fun invoke(id: String): ApiResult<Unit> {
        return repository.deleteNote(id)
    }
}