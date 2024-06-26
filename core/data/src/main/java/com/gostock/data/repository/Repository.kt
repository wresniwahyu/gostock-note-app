package com.gostock.data.repository

import com.gostock.data.util.ApiResult
import com.gostock.data.model.BaseUiModel
import com.gostock.data.model.GetNotesUiModel
import com.gostock.network.model.LoginBody
import com.gostock.network.model.PostNoteBody
import com.gostock.network.model.RegisterBody

interface Repository {

    suspend fun register(body: RegisterBody): ApiResult<BaseUiModel>
    suspend fun login(body: LoginBody): ApiResult<BaseUiModel>
    suspend fun getNotes(): ApiResult<GetNotesUiModel>
    suspend fun postNote(body: PostNoteBody): ApiResult<Unit>
    suspend fun updateNote(id: String, body: PostNoteBody): ApiResult<Unit>
    suspend fun deleteNote(id: String): ApiResult<Unit>
    suspend fun storeAccessToken(token: String)
    suspend fun storeUserName(username: String)
    suspend fun clearSession()

}