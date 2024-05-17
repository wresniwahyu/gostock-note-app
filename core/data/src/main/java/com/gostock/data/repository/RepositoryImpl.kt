package com.gostock.data.repository

import com.gostock.data.util.ApiResult
import com.gostock.data.util.Mapper
import com.gostock.data.util.handleApi
import com.gostock.network.model.BaseResponseDto
import com.gostock.data.model.BaseUiModel
import com.gostock.data.model.GetNotesUiModel
import com.gostock.local.UserPref
import com.gostock.network.model.GetNotesResponseDto
import com.gostock.network.model.LoginBody
import com.gostock.network.model.PostNoteBody
import com.gostock.network.model.RegisterBody
import com.gostock.network.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userPref: UserPref,
    private val baseMapper: Mapper<BaseResponseDto, BaseUiModel>,
    private val getNotesUiMapper: Mapper<GetNotesResponseDto, GetNotesUiModel>,
): Repository {

    override suspend fun register(body: RegisterBody): ApiResult<BaseUiModel> {
        return handleApi(baseMapper) {
            apiService.register(body)
        }
    }

    override suspend fun login(body: LoginBody): ApiResult<BaseUiModel> {
        return handleApi(baseMapper) {
            apiService.login(body)
        }
    }

    override suspend fun getNotes(): ApiResult<GetNotesUiModel> {
        return handleApi(getNotesUiMapper) {
            apiService.getNotes()
        }
    }

    override suspend fun postNote(body: PostNoteBody): ApiResult<Unit> {
        return handleApi {
            apiService.postNote(body)
        }
    }

    override suspend fun updateNote(id: String, body: PostNoteBody): ApiResult<Unit> {
        return handleApi {
            apiService.updateNote(id, body)
        }
    }

    override suspend fun deleteNote(id: String): ApiResult<Unit> {
        return handleApi {
            apiService.deleteNote(id)
        }
    }

    override suspend fun storeAccessToken(token: String) {
        withContext(Dispatchers.IO) {
            userPref.accessToken = token
        }
    }

    override suspend fun storeUserName(username: String) {
        withContext(Dispatchers.IO) {
            userPref.username = username
        }
    }

    override suspend fun clearSession() {
        withContext(Dispatchers.IO) {
            userPref.clear()
        }
    }
}