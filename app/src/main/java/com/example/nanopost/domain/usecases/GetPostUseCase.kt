package com.example.nanopost.domain.usecases

import com.example.nanopost.data.repository.ApiRepository
import com.example.nanopost.domain.model.Post
import javax.inject.Inject


class GetPostUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(postId: String): Post {
        return apiRepository.getPost(postId)

    }
}