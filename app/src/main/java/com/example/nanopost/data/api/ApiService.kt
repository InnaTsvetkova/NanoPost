package com.example.nanopost.data.api

import com.example.nanopost.data.api.models.ApiImage
import com.example.nanopost.data.api.models.ApiPost
import com.example.nanopost.data.api.models.ApiProfile
import com.example.nanopost.data.api.models.ResultResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @GET("feed")
    suspend fun getFeed(
        @Query("count") count: Int?,
        @Query("offset") offset: String?
    ): PageDataResponse<ApiPost>

    @GET("post/{postId}")
    suspend fun getPost(
        @Path("postId") postId: String
    ): ApiPost


    @GET("profile/{profileId}")
    suspend fun getProfile(
        @Path("profileId") profileId: String
    ): ApiProfile


    @PUT("post")
    @Multipart
    suspend fun createPost(
        @Part("text") text: RequestBody?,
        @Part image1: MultipartBody.Part?,
        @Part image2: MultipartBody.Part?,
        @Part image3: MultipartBody.Part?,
        @Part image4: MultipartBody.Part?
    ): ApiPost

    @GET("posts/{profileId}")
    suspend fun getPosts(
        @Path("profileId") profileId: String,
        @Query("count") count: Int?,
        @Query("offset") offset: String?
    ): PageDataResponse<ApiPost>

    @GET("images/{profileId}")
    suspend fun getImages(
        @Path("profileId") profileId: String,
        @Query("count") count: Int?,
        @Query("offset") offset: String?
    ): PageDataResponse<ApiImage>


    @GET("image/{imageId}")
    suspend fun getImage(
        @Path("imageId") imageId: String
    ): ApiImage

    @DELETE("post/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: String
    ): ResultResponse

    @DELETE("image/{imageId}")
    suspend fun deleteImage(
        @Path("imageId") imageId: String
    ): ResultResponse
}