package com.example.nanopost.domain.usecases

import com.example.nanopost.data.repository.ApiRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(postId: String) : Boolean{
        return apiRepository.deletePost(postId)
    }
}