package com.example.nanopost.data.repository

import com.example.nanopost.data.api.models.AuthData
import com.example.nanopost.data.api.models.CheckUsernameResult
import com.example.nanopost.data.api.models.CheckUsernameResultResponse
import com.example.nanopost.data.api.models.TokenResponse

interface AuthRepository {
    suspend fun checkUsername(username: String): CheckUsernameResult

    suspend fun login(username: String, password: String): TokenResponse

    suspend fun register(username: String, password: String): TokenResponse
}