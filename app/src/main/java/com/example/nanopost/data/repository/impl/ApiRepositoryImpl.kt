package com.example.nanopost.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.nanopost.data.api.ApiService
import com.example.nanopost.data.api.models.ApiPost
import com.example.nanopost.data.repository.ApiRepository
import com.example.nanopost.data.mapper.ImageMapper
import com.example.nanopost.data.mapper.PostMapper
import com.example.nanopost.data.mapper.ProfileMapper
import com.example.nanopost.data.paging.StringKeyPagingSource
import com.example.nanopost.domain.model.Image
import com.example.nanopost.domain.model.Post
import com.example.nanopost.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject


class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val postMapper: PostMapper,
    private val profileMapper: ProfileMapper,
    private val imageMapper: ImageMapper
) : ApiRepository {

    override suspend fun getPost(postId: String): Post {
        val apiPost = apiService.getPost(postId)
        return postMapper.transform(apiPost)
    }

    override suspend fun getFeed(): Flow<PagingData<Post>> {
        return Pager<String, ApiPost>(
            PagingConfig(30, enablePlaceholders = false),
            "0"
        ) {
            StringKeyPagingSource() {
                apiService.getFeed(it.loadSize, it.key)
            }
        }.flow.map {
            it.map { apiPost ->
                postMapper.transform(apiPost)
            }
        }
    }

    override suspend fun getProfile(profileId: String): Profile {
        val apiProfile = apiService.getProfile(profileId)
        return profileMapper.transform(apiProfile)
    }

    override suspend fun getPosts(
        profileId: String,
    ): Flow<PagingData<Post>> {
        return Pager(
            PagingConfig(5, enablePlaceholders = false), "0"
        ) {
            StringKeyPagingSource() {
                apiService.getPosts(profileId, it.loadSize, it.key)
            }
        }.flow.map {
            it.map { apiPost ->
                postMapper.transform(apiPost)
            }
        }

    }


    override suspend fun getImages(
        profileId: String
    ): Flow<PagingData<Image>> {
       return Pager(
           PagingConfig(4, enablePlaceholders = false), "0"
       ) {
           StringKeyPagingSource() {
               apiService.getImages(profileId, it.loadSize, it.key)
           }
       }.flow.map {
           it.map { apiImage ->
               imageMapper.transform(apiImage)
           }
       }
    }

    override suspend fun getImage(imageId: String): Image {
       val apiImage = apiService.getImage(imageId)
        return imageMapper.transform(apiImage)
    }

    override suspend fun createPost(text: String?, images: List<ByteArray>?): Post {
        val image1 = images?.getOrNull(0)?.let {
            MultipartBody.Part.createFormData(
                "image1",
                "image1.jpg",
                it.toRequestBody("image/jpg".toMediaType())
            )
        }

        val image2 = images?.getOrNull(1)?.let{
            MultipartBody.Part.createFormData(
                "image2",
                "image2.jpg",
                it.toRequestBody("image/jpg".toMediaType())
            )
        }

        val image3 = images?.getOrNull(2)?.let{
            MultipartBody.Part.createFormData(
                "image3",
                "image3.jpg",
                it.toRequestBody("image/jpg".toMediaType())
            )
        }

        val image4 = images?.getOrNull(3)?.let{
            MultipartBody.Part.createFormData(
                "image4",
                "image4.jpg",
                it.toRequestBody("image/jpg".toMediaType())
            )
        }

        val apiPost = apiService.createPost(
             text?.toRequestBody(),
            image1,
            image2,
            image3,
            image4
        )
        return postMapper.transform(apiPost)
    }

    override suspend fun deletePost(postId: String): Boolean {
       return apiService.deletePost(postId).result
    }

    override suspend fun deleteImage(imageId: String): Boolean {
        return apiService.deleteImage(imageId).result
    }
}