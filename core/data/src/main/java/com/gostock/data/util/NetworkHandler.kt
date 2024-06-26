package com.gostock.data.util

import com.gostock.util.extension.fromJson
import retrofit2.HttpException
import retrofit2.Response

sealed interface ApiResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
class ApiError<T : Any>(
    val throwable: Throwable? = null,
    val code: Int? = null,
    val message: String? = null,
    val error: Error? = null,
) : ApiResult<T>


suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    var error: Error? = null
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiSuccess(body)
        } else {
            val errorBody = response.errorBody()?.string()
            if (errorBody != null) {
                error = errorBody.fromJson(Error::class)
            }
            ApiError(code = response.code(), message = error?.message, error = error)
        }
    } catch (e: HttpException) {
        ApiError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiError(throwable = e)
    }
}

@Suppress("UNCHECKED_CAST")
suspend fun <T : Any, R : Any> handleApi(
    mapper: Mapper<T, R>,
    execute: suspend () -> Response<T>
): ApiResult<R> {
    var error: Error? = null

    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            response.body()?.let {
                return ApiSuccess(mapper.map(it))
            } ?: run {
                return ApiSuccess(Unit as R)
            }
        } else {
            val errorBody = response.errorBody()?.string()
            if (errorBody != null) {
                error = errorBody.fromJson(Error::class)
            }
            ApiError(code = response.code(), message = error?.message, error = error)
        }
    } catch (e: HttpException) {
        ApiError(code = e.code(), message = e.message(), error = error)
    } catch (e: Throwable) {
        ApiError(throwable = e)
    }
}

suspend fun <T : Any> ApiResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): ApiResult<T> = apply {
    if (this is ApiSuccess<T>) {
        executable(data)
    }
}

suspend fun <T : Any> ApiResult<T>.onError(
    executable: suspend (e: Throwable?, code: Int?, message: String?, error: Error?) -> Unit
): ApiResult<T> = apply {
    if (this is ApiError<T>) {
        executable(throwable, code, message, error)
    }
}