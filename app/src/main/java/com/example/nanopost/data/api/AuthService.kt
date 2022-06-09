package com.example.nanopost.data.api

import com.example.nanopost.data.api.models.AuthData
import com.example.nanopost.data.api.models.CheckUsernameResultResponse
import com.example.nanopost.data.api.models.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @GET("checkUsername")
    suspend fun checkUsername(
        @Query("username") username: String
    ): CheckUsernameResultResponse

    @GET("login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): TokenResponse

    @POST("register")
    suspend fun register(
        @Body authData: AuthData
    ): TokenResponse
}