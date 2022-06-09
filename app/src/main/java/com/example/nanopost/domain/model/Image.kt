package com.example.nanopost.domain.model

data class Image(
    val id: String,
    val owner: ProfileCompact,
    val dateCreated: Long,
    val sizes: List<ImageSize>
)