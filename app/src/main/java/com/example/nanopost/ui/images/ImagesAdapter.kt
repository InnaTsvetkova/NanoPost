package com.example.nanopost.ui.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nanopost.databinding.ItemImageBinding
import com.example.nanopost.databinding.ItemPostBinding
import com.example.nanopost.domain.model.Image
import com.example.nanopost.domain.model.Post
import com.example.nanopost.ui.shared.PostAdapter
import com.example.nanopost.ui.utils.formatDate

class ImageAdapter(
) : PagingDataAdapter<Image, ImageAdapter.ImageViewHolder>(ImageItemCallback) {

    private lateinit var onImageClick: (Image) -> Unit

    fun setOnImageClick(callback: (Image) -> Unit) {
        onImageClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ImageViewHolder(private val viewBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: Image) {
            viewBinding.image.load(item.sizes.getOrNull(1)?.url)

            viewBinding.image.setOnClickListener {
                onImageClick(item)
            }
        }
    }

}

private object ImageItemCallback : DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}