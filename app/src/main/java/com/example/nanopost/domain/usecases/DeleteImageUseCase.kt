package com.example.nanopost.domain.usecases

import com.example.nanopost.data.repository.ApiRepository
import javax.inject.Inject

class DeleteImageUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(imageId: String): Boolean{
        return apiRepository.deleteImage(imageId)
    }
}