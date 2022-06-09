package com.example.nanopost.ui.post

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.example.nanopost.databinding.ItemImagePostBinding
import com.example.nanopost.domain.model.Image

class ImageAdapter() : ListAdapter<Image, ImageAdapter.ImageViewHolder>(ImagePostCallback) {

    private lateinit var onImageClick: (Image) -> Unit

    fun setOnImageClick(callback: (Image) -> Unit) {
        onImageClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ItemImagePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(private val viewBinding: ItemImagePostBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: Image) {
            viewBinding.image.apply {
                load(item.sizes.first().url)
                setOnClickListener {
                    onImageClick(item)
                }
            }
        }
    }
}

private object ImagePostCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}