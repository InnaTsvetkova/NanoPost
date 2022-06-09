package com.example.nanopost.ui.createPost

import android.net.Uri
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nanopost.databinding.FragmentCreatePostBinding
import com.example.nanopost.databinding.ItemAddImageBinding
import com.example.nanopost.databinding.ItemImageBinding
import com.example.nanopost.databinding.ItemPostBinding
import com.example.nanopost.domain.model.Post

class CreatePostAdapter(
) : ListAdapter<Uri, CreatePostAdapter.CreatePostViewHolder>(CreatePostItemCallback) {

    private lateinit var onCancelClick: (Uri) -> Unit

    fun setOnCancelClick(callback: (Uri) -> Unit){
        onCancelClick = callback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreatePostViewHolder {
        val binding =
            ItemAddImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CreatePostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreatePostAdapter.CreatePostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CreatePostViewHolder(private val viewBinding: ItemAddImageBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: Uri) {
            viewBinding.image.load(item)
            viewBinding.cancel.setOnClickListener{
                onCancelClick(item)
            }
        }

    }
}

private object CreatePostItemCallback : DiffUtil.ItemCallback<Uri>() {

    override fun areItemsTheSame(oldItem: Uri, newItem: Uri): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Uri, newItem: Uri): Boolean {
        return oldItem == newItem
    }
}