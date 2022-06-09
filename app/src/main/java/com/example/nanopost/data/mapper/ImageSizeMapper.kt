package com.example.nanopost.data.mapper

import com.example.nanopost.data.api.models.ApiImageSize
import com.example.nanopost.domain.model.ImageSize
import javax.inject.Inject

class ImageSizeMapper @Inject constructor(

) : BaseMapper<ApiImageSize, ImageSize>(){

    override fun transform(entity: ApiImageSize)= ImageSize(
        height = entity.height,
        width = entity.width,
        url = entity.url
    )
}