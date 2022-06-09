package com.example.nanopost.data.api.models

import com.example.nanopost.domain.model.ImageSize
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiImageSize(
    val height: Int,
    val width: Int,
    val url: String
)