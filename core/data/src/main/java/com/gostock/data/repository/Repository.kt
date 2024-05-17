package com.gostock.data.repository

import com.gostock.data.util.ApiResult
import com.gostock.data.model.BaseUiModel
import com.gostock.network.model.LoginBody
import com.gostock.network.model.PostNoteBody
import com.gostock.network.model.RegisterBody

interface Repository {

    suspend fun register(body: RegisterBody): ApiResult<Unit>
    suspend fun login(body: LoginBody): ApiResult<BaseUiModel>
    suspend fun getNotes(): ApiResult<Unit>
    suspend fun postNote(body: PostNoteBody): ApiResult<Unit>
    suspend fun updateBody(id: String, body: PostNoteBody): ApiResult<Unit>
    suspend fun deleteBody(id: String): ApiResult<Unit>

}