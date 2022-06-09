package com.example.nanopost.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanopost.domain.model.Post
import com.example.nanopost.domain.usecases.DeletePostUseCase
import com.example.nanopost.domain.usecases.GetPostUseCase
import com.example.nanopost.domain.usecases.GetPostsUseCase
import com.example.nanopost.domain.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {
    val postLiveData = MutableLiveData<Post>()
    val deletePostLiveData = MutableLiveData<Boolean>()
    val showMenuLiveData = MutableLiveData<Boolean>()

    fun getPost(postId: String) {
        viewModelScope.launch {
            val post = getPostUseCase.invoke(postId)
            postLiveData.value = post
            showMenuLiveData.value = post.owner.id == getUserIdUseCase.invoke()
        }
    }

    fun deletePost() {
        viewModelScope.launch {
            val postId = postLiveData.value?.id ?: error("postId is null")
            deletePostLiveData.value = deletePostUseCase.invoke(postId)
        }
    }

}