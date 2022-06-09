package com.example.nanopost.data.mapper

import com.example.nanopost.data.api.models.ApiProfileCompact
import com.example.nanopost.domain.model.ProfileCompact
import javax.inject.Inject

class ProfileCompatMapper @Inject constructor(

) : BaseMapper<ApiProfileCompact, ProfileCompact>(){

    override fun transform(entity: ApiProfileCompact)= ProfileCompact(
        id = entity.id,
        username =entity.username,
        displayName = entity.displayName,
        avatarUrl = entity.avatarUrl,
        subscribed = entity.subscribed
    )
}