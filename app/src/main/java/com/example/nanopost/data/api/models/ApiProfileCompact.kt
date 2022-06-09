package com.example.nanopost.data.api.models

import com.example.nanopost.domain.model.ProfileCompact
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiProfileCompact(
    val id: String,
    val username: String,
    val displayName: String?,
    val avatarUrl: String?,
    val subscribed: Boolean
)