package com.example.nanopost.data.repository.impl

import com.example.nanopost.data.api.AuthService
import com.example.nanopost.data.api.models.AuthData
import com.example.nanopost.data.api.models.CheckUsernameResult
import com.example.nanopost.data.api.models.TokenResponse
import com.example.nanopost.data.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun checkUsername(username: String): CheckUsernameResult {
        return authService.checkUsername(username).result
    }

    override suspend fun login(username: String, password: String): TokenResponse {
        return authService.login(username, password)
    }

    override suspend fun register(username: String, password: String): TokenResponse {
        val authData = AuthData(username, password)
        return authService.register(authData)
    }
}