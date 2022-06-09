package com.example.nanopost.data.api.models

import com.example.nanopost.domain.model.Image
import com.example.nanopost.domain.model.Profile
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiProfile(
    val id: String,
    val username: String,
    val displayName: String?,
    val bio: String?,
    val avatarId: String?,
    val avatarSmall: String?,
    val avatarLarge: String?,
    val subscribed: Boolean,
    val subscribersCount: Int,
    val postsCount: Int,
    val imagesCount: Int,
    val images: List<ApiImage>
)