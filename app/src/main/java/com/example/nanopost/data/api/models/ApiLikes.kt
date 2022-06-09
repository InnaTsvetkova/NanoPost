package com.example.nanopost.data.api.models

import com.example.nanopost.domain.model.Likes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiLikes(
    val liked: Boolean,
    val likesCount: Int
)