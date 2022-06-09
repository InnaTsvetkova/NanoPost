package com.example.nanopost.domain.usecases

import android.content.ContentResolver
import android.net.Uri
import javax.inject.Inject

class GetByteArrayUseCase @Inject constructor(
    private val contentResolver: ContentResolver
) {
    operator fun invoke(uris: List<Uri>?): List<ByteArray>{
       return uris.orEmpty().map {
            contentResolver.openInputStream(it)?.readBytes() ?: error("ByteArray is null")
        }
    }
}