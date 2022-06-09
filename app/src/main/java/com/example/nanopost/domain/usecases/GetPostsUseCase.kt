package com.example.nanopost.domain.usecases

import androidx.paging.PagingData
import com.example.nanopost.data.repository.ApiRepository
import com.example.nanopost.domain.model.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(userId: String) : Flow<PagingData<Post>> {
        return apiRepository.getPosts(userId)
    }
}