package com.example.nanopost.data.repository

import androidx.paging.PagingData
import com.example.nanopost.domain.model.Image
import com.example.nanopost.domain.model.Post
import com.example.nanopost.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getFeed(): Flow<PagingData<Post>>

    suspend fun getPost(postId: String): Post

    suspend fun getProfile(profileId: String): Profile

    suspend fun getPosts(profileId: String): Flow<PagingData<Post>>

    suspend fun getImages(profileId: String): Flow<PagingData<Image>>

    suspend fun getImage(imageId: String): Image

    suspend fun createPost(text: String?, images: List<ByteArray>?): Post

    suspend fun deletePost(postId: String): Boolean

    suspend fun deleteImage(imageId: String): Boolean

}