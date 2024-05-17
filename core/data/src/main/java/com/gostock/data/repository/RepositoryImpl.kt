package com.gostock.data.repository

import com.gostock.data.util.ApiResult
import com.gostock.data.util.Mapper
import com.gostock.data.util.handleApi
import com.gostock.network.model.BaseResponseDto
import com.gostock.data.model.BaseUiModel
import com.gostock.network.model.LoginBody
import com.gostock.network.model.PostNoteBody
import com.gostock.network.model.RegisterBody
import com.gostock.network.service.ApiService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val baseMapper: Mapper<BaseResponseDto, BaseUiModel>,
): Repository {

    override suspend fun register(body: RegisterBody): ApiResult<Unit> {
        return handleApi {
            apiService.register(body)
        }
    }

    override suspend fun login(body: LoginBody): ApiResult<BaseUiModel> {
        return handleApi(baseMapper) {
            apiService.login(body)
        }
    }

    override suspend fun getNotes(): ApiResult<Unit> {
        return handleApi {
            apiService.getNotes()
        }
    }

    override suspend fun postNote(body: PostNoteBody): ApiResult<Unit> {
        return handleApi {
            apiService.postNote(body)
        }
    }

    override suspend fun updateBody(id: String, body: PostNoteBody): ApiResult<Unit> {
        return handleApi {
            apiService.updateNote(id, body)
        }
    }

    override suspend fun deleteBody(id: String): ApiResult<Unit> {
        return handleApi {
            apiService.deleteNote(id)
        }
    }
}