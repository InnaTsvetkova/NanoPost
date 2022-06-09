package com.example.nanopost.domain.usecases

import com.example.nanopost.data.repository.ApiRepository
import com.example.nanopost.domain.model.Profile
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(profileId: String) : Profile {
        return apiRepository.getProfile(profileId)
    }
}