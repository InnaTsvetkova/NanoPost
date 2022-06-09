package com.example.nanopost.data.mapper

import com.example.nanopost.data.api.models.ApiPost
import com.example.nanopost.domain.model.Post
import javax.inject.Inject

class PostMapper @Inject constructor(
    private val profileCompactMapper: ProfileCompatMapper,
    private val imageMapper: ImageMapper,
    private val likesMapper: LikesMapper
) : BaseMapper<ApiPost, Post>() {

    override fun transform(entity: ApiPost) = Post(
        id = entity.id,
        owner = profileCompactMapper.transform(entity.owner),
        dateCreated = entity.dateCreated,
        text = entity.text,
        images = imageMapper.transform(entity.images),
        likes = likesMapper.transform(entity.likes)
    )
}