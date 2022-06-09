package com.example.nanopost.ui.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.nanopost.domain.model.Post
import com.example.nanopost.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    val postsLiveData = MutableLiveData<PagingData<Post>>()

    //Так как нет подписок, в ленте отображаются посты Олега
    fun getFeed() {
        viewModelScope.launch {
            getPostsUseCase.invoke("evo").collect {
                postsLiveData.value = it
            }
        }
    }

}