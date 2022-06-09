package com.example.nanopost.ui.image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanopost.domain.model.Image
import com.example.nanopost.domain.usecases.DeleteImageUseCase
import com.example.nanopost.domain.usecases.GetImageUseCase
import com.example.nanopost.domain.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase,
    private val deleteImageUseCase: DeleteImageUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {

    val imageLiveData = MutableLiveData<Image>()
    val deleteImageLiveData = MutableLiveData<Boolean>()
    val showMenuLiveData = MutableLiveData<Boolean>()

    fun getImage(imageId: String) {
        viewModelScope.launch {
            val image = getImageUseCase.invoke(imageId)
            imageLiveData.value = image
            showMenuLiveData.value = image.owner.id == getUserIdUseCase.invoke()
        }
    }

    fun deleteImage() {
        val imageId = imageLiveData.value?.id ?: error("imageId is null")
        viewModelScope.launch {
            deleteImageLiveData.value = deleteImageUseCase.invoke(imageId)
        }
    }
}