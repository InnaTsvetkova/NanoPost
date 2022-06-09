package com.example.nanopost.ui.createPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nanopost.services.PostCreateService
import com.example.nanopost.R
import com.example.nanopost.databinding.FragmentCreatePostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePostFragment : Fragment() {
    private lateinit var binding: FragmentCreatePostBinding
    private val viewModel by viewModels<CreatePostViewModel>()
    private val createPostAdapter = CreatePostAdapter()
    private val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let {
            viewModel.onImagePick(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createPostRecycler.apply {
            adapter = createPostAdapter.apply {
                setOnCancelClick {
                    viewModel.onImageRemoved(it)
                }
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonAddImage.setOnClickListener {
            imagePicker.launch("image/*")
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.accept -> {
                    requireContext().startService(
                        PostCreateService.newIntent(
                            requireContext(),
                            binding.newPostText.text.toString(),
                            viewModel.uriLiveData.value.orEmpty()
                        )
                    )
                    findNavController().popBackStack()
                    true
                }
                else -> false
            }
        }
        viewModel.uriLiveData.observe(viewLifecycleOwner) {
            createPostAdapter.submitList(it)
        }
    }
}