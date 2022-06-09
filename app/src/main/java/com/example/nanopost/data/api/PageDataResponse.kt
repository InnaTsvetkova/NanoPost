package com.example.nanopost.data.api


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageDataResponse<T>(
    val count: Int,
    val total: Int,
    val offset: String?,
    val items: List<T>
)