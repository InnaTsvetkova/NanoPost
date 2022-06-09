package com.example.nanopost.ui.createPost

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
) : ViewModel() {

    val uriLiveData = MutableLiveData<List<Uri>>()

    fun onImagePick(uri: Uri) {
        val list = uriLiveData.value.orEmpty()
        uriLiveData.value = list.toMutableList().apply {
            add(uri)
        }
    }

    fun onImageRemoved(uri: Uri){
        val list = uriLiveData.value.orEmpty()
        uriLiveData.value = list.toMutableList().apply {
            remove(uri)
        }
    }
}