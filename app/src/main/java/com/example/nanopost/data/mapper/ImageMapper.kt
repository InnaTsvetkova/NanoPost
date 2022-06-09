package com.example.nanopost.data.mapper

import com.example.nanopost.data.api.models.ApiImage
import com.example.nanopost.domain.model.Image
import javax.inject.Inject

class ImageMapper @Inject constructor(
    private val profileCompatMapper: ProfileCompatMapper,
    private val imageSizeMapper: ImageSizeMapper
) : BaseMapper<ApiImage, Image>() {

    override fun transform(entity: ApiImage) = Image(
        id = entity.id,
        owner = profileCompatMapper.transform(entity.owner),
        dateCreated = entity.dateCreated,
        sizes = imageSizeMapper.transform(entity.sizes)
    )
}