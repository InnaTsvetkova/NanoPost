package com.example.nanopost.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nanopost.NavGraphDirections
import com.example.nanopost.databinding.FragmentImagesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding
    private val imagesAdapter = ImageAdapter()
    private val viewModel by viewModels<ImagesViewModel>()
    private val args: ImagesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getImages(args.profileId)

        binding.imagesRecycler.apply {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
            adapter = imagesAdapter.apply {
                setOnImageClick {
                    val action = NavGraphDirections.actionGlobalImage(it.id)
                    findNavController().navigate(action)
                }
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.imagesLiveData.observe(viewLifecycleOwner) {
            imagesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}