package com.gostock.data.usecase

import com.gostock.data.repository.Repository
import com.gostock.data.util.ApiResult
import com.gostock.network.model.PostNoteBody
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(
    private val repository: Repository
) : AddNoteUseCase {

    override suspend fun invoke(title: String, note: String): ApiResult<Unit> {
        return repository.postNote(
            PostNoteBody(
                title = title,
                note = note
            )
        )
    }
}