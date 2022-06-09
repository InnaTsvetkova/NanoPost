package com.example.nanopost.ui.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.nanopost.domain.model.Image
import com.example.nanopost.domain.usecases.GetImagesUseCase
import com.example.nanopost.domain.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {

    val imagesLiveData = MutableLiveData<PagingData<Image>>()

    fun getImages(profileId: String?) {
        viewModelScope.launch {
            getImagesUseCase.invoke(
                profileId ?: getUserIdUseCase.invoke()
            ).collect {
                imagesLiveData.value = it
            }
        }

    }
}