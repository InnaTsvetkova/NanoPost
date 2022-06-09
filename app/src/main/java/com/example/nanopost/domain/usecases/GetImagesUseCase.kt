package com.example.nanopost.domain.usecases

import androidx.paging.PagingData
import com.example.nanopost.data.repository.ApiRepository
import com.example.nanopost.domain.model.Image
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(userId: String): Flow<PagingData<Image>> {
        return apiRepository.getImages(userId)
    }
}