package com.gostock.network.service

import com.gostock.network.model.BaseResponseDto
import com.gostock.network.model.LoginBody
import com.gostock.network.model.PostNoteBody
import com.gostock.network.model.RegisterBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("register")
    suspend fun register(
        @Body body: RegisterBody
    ): Response<Unit>

    @POST("login")
    suspend fun login(
        @Body body: LoginBody
    ): Response<BaseResponseDto>

    @GET("notes")
    suspend fun getNotes(): Response<Unit>

    @POST("note")
    suspend fun postNote(
        @Body body: PostNoteBody
    ): Response<Unit>

    @PUT("note/{id}")
    suspend fun updateNote(
        @Path("id") id: String,
        @Body body: PostNoteBody
    ): Response<Unit>

    @DELETE("note/{id}")
    suspend fun deleteNote(
        @Path("id") id: String,
    ): Response<Unit>

}