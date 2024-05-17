package com.gostock.data.usecase

import com.gostock.data.repository.Repository
import com.gostock.data.util.ApiResult
import com.gostock.network.model.PostNoteBody
import javax.inject.Inject

class UpdateNoteUseCaseImpl @Inject constructor(
    private val repository: Repository
) : UpdateNoteUseCase {

    override suspend fun invoke(id: String, title: String, note: String): ApiResult<Unit> {
        return repository.updateNote(
            id = id,
            body = PostNoteBody(
                title = title,
                note = note
            )
        )
    }
}