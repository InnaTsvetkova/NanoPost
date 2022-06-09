package com.example.nanopost.data.api.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    val token: String,
    val userId: String
)