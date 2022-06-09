package com.example.nanopost.data.api.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultResponse(
    val result: Boolean
)