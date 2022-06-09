package com.example.nanopost.ui.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nanopost.databinding.ItemPostBinding
import com.example.nanopost.domain.model.Image
import com.example.nanopost.domain.model.Post
import com.example.nanopost.ui.utils.formatDate
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.time.Duration.Companion.days
import kotlin.time.toDuration

class PostAdapter(
) : PagingDataAdapter<Post, PostAdapter.PostViewHolder>(PostItemCallback) {

    private lateinit var onPostClick: (Post) -> Unit

    private lateinit var onImageClick: (Image) -> Unit

    @JvmName("setOnItemPostClick")
    fun setOnPostClick(callBack: (Post) -> Unit) {
        onPostClick = callBack
    }

    @JvmName("setOnItemImageClick")
    fun setOnImageClick(callBack: (Image) -> Unit) {
        onImageClick = callBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class PostViewHolder(private val viewBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: Post) {
            viewBinding.avatar.load(item.owner.avatarUrl)
            viewBinding.displayName.text = item.owner.displayName ?: item.owner.username
            if (item.text == null) {
                viewBinding.postText.isVisible = false
            } else {
                viewBinding.postText.text = item.text
            }
            viewBinding.dateCreated.text = formatDate(item.dateCreated)

            val imageOrNull = item.images.getOrNull(0)
            if (imageOrNull == null) {
                viewBinding.image.isVisible = false
            } else {
                viewBinding.image.load(imageOrNull.sizes.first().url)
            }
            viewBinding.likes.text = item.likes.likesCount.toString()

            viewBinding.root.setOnClickListener {
                onPostClick(item)
            }

            viewBinding.image.setOnClickListener {
                onImageClick(item.images.first())
            }
        }
    }

}


private object PostItemCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}