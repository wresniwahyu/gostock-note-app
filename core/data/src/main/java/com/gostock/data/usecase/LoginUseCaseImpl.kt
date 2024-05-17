package com.gostock.data.usecase

import android.util.Log
import com.gostock.data.model.BaseUiModel
import com.gostock.data.repository.Repository
import com.gostock.data.util.ApiResult
import com.gostock.data.util.onSuccess
import com.gostock.network.model.LoginBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: Repository
) : LoginUseCase {
    override suspend fun invoke(email: String, password: String): ApiResult<BaseUiModel> {
        val result = repository.login(
            LoginBody(
                email = email,
                password = password
            )
        )

        withContext(Dispatchers.IO) {
            result.onSuccess {
                if (it.success) {
                    try {
                        val jsonObject = JSONObject(it.data.toString().trim())
                        val token: String? = jsonObject.getString("token")
                        token?.let {
                            repository.storeAccessToken(it)
                        }
                    } catch (e: Exception) {
                        Log.i("STORE_TOKEN", "failed store token")
                    }
                }
            }
        }

        return result
    }
}