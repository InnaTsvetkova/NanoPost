package com.example.nanopost.domain.usecases

import com.example.nanopost.data.repository.ApiRepository
import com.example.nanopost.domain.model.Image
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(imageId: String): Image {
        return apiRepository.getImage(imageId)
    }

}