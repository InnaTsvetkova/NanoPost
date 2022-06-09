package com.example.nanopost.data.mapper

import com.example.nanopost.data.api.models.ApiLikes
import com.example.nanopost.domain.model.Likes
import javax.inject.Inject

class LikesMapper @Inject constructor(

) : BaseMapper<ApiLikes, Likes>(){

    override fun transform(entity: ApiLikes)= Likes(
        liked = entity.liked,
        likesCount = entity.likesCount
    )
}