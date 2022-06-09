package com.example.nanopost.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nanopost.data.api.PageDataResponse
import com.example.nanopost.domain.model.Image
import com.example.nanopost.domain.model.Post
import com.example.nanopost.domain.model.Profile
import com.example.nanopost.domain.usecases.GetPostsUseCase
import com.example.nanopost.domain.usecases.GetProfileUseCase
import com.example.nanopost.domain.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    val profileLiveData = MutableLiveData<Profile>()

    val postsLiveData = MutableLiveData<PagingData<Post>>()

    fun init(profileId: String?) {
        getProfile(profileId)
        getPosts(profileId)
    }

    fun onImageClick(index: Int,callback: (Image) -> Unit) {
        val image = profileLiveData.value?.images?.get(index) ?: error("Image is null")
        callback.invoke(image)
    }

    private fun getProfile(profileId: String?) {
        viewModelScope.launch {
            profileLiveData.value =
                getProfileUseCase.invoke(profileId ?: getUserIdUseCase.invoke())
        }
    }

    private fun getPosts(profileId: String?) {
        viewModelScope.launch {
            getPostsUseCase.invoke(
                profileId ?: getUserIdUseCase.invoke()
            ).collect {
                postsLiveData.value = it
            }
        }

    }
}
