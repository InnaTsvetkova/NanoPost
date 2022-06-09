package com.example.nanopost.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.nanopost.NavGraphDirections
import com.example.nanopost.R
import com.example.nanopost.databinding.FragmentPostBinding
import com.example.nanopost.ui.utils.formatDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding
    private val viewModel: PostViewModel by viewModels()
    private val args: PostFragmentArgs by navArgs()
    private val imageAdapter = ImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceSaved: Bundle?,
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPost(args.postId)

        binding.postRecycler.apply {
            adapter = imageAdapter.apply {
                setOnImageClick {
                    val action = NavGraphDirections.actionGlobalImage(it.id)
                    findNavController().navigate(action)
                }
            }



            binding.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            binding.toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.delete -> {
                        viewModel.deletePost()
                        true
                    }
                    else -> false
                }
            }

            binding.avatar.setOnClickListener {
                val profileId = viewModel.postLiveData.value?.owner?.id
                profileId?.let { id ->
                    //не получается через saveargs передать аргумент
                    findNavController().navigate(
                        R.id.action_postFragment_to_profileFragment, bundleOf("profileId" to id)
                    )
                }

            }


            viewModel.postLiveData.observe(viewLifecycleOwner) {
                binding.apply {
                    avatar.load(it.owner.avatarUrl)
                    displayName.text = it.owner.displayName ?: it.owner.username
                    dateCreated.text = formatDate(it.dateCreated)
                    postText.text = it.text

                    imageAdapter.submitList(it.images)
                }
            }

            viewModel.deletePostLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().popBackStack()
                }
            }

            viewModel.showMenuLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    binding.toolbar.inflateMenu(R.menu.menu_delete)
                }
            }

        }

    }
}
