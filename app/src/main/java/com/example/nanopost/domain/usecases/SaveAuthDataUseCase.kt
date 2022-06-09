package com.example.nanopost.domain.usecases

import com.example.nanopost.data.repository.PrefsRepository
import com.example.nanopost.data.api.models.TokenResponse
import javax.inject.Inject

class SaveAuthDataUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {
    operator fun invoke(tokenResponse: TokenResponse) {
        prefsRepository.apply {
            putToken(tokenResponse.token)
            putUserId(tokenResponse.userId)
        }

    }
}