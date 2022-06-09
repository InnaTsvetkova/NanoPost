package com.example.nanopost.data.mapper

import com.example.nanopost.data.api.models.ApiProfile
import com.example.nanopost.domain.model.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor(
    private val imageMapper: ImageMapper
) : BaseMapper<ApiProfile, Profile>() {

    override fun transform(entity: ApiProfile) = Profile(
        id = entity.id,
        username = entity.username,
        displayName = entity.displayName,
        bio = entity.bio,
        avatarId = entity.avatarId,
        avatarSmall = entity.avatarSmall,
        avatarLarge = entity.avatarLarge,
        subscribed = entity.subscribed,
        subscribersCount = entity.subscribersCount,
        postsCount = entity.postsCount,
        imagesCount = entity.imagesCount,
        images = imageMapper.transform(entity.images)
    )
}