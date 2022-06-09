package com.example.nanopost.domain.usecases

import com.example.nanopost.data.api.models.CheckUsernameResult
import com.example.nanopost.data.repository.AuthRepository
import javax.inject.Inject

class CheckUsernameUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String): CheckUsernameResult {
        return authRepository.checkUsername(username)
    }
}