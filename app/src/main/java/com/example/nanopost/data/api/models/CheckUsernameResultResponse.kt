package com.example.nanopost.data.api.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckUsernameResultResponse(
    val result: CheckUsernameResult
)

@JsonClass(generateAdapter = false)
enum class CheckUsernameResult{
    TooShort,
    TooLong,
    InvalidCharacters,
    Taken,
    Free
}