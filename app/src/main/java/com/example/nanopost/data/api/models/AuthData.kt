package com.example.nanopost.data.api.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthData(
    val username: String,
    val password: String
)