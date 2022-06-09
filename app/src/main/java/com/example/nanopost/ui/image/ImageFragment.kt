package com.example.nanopost.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.nanopost.R
import com.example.nanopost.databinding.FragmentImageBinding
import com.example.nanopost.ui.utils.formatDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding
    private val viewModel: ImageViewModel by viewModels()
    private val args: ImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceSaved: Bundle?,
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getImage(args.imageId)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete -> {
                    viewModel.deleteImage()
                    true
                }
                else -> false
            }
        }

        viewModel.imageLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                avatar.load(it.owner.avatarUrl)
                displayName.text = it.owner.displayName ?: it.owner.username
                dateCreated.text = formatDate(it.dateCreated)
                image.load(it.sizes[0].url)
            }
        }

        viewModel.deleteImageLiveData.observe(viewLifecycleOwner) {
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