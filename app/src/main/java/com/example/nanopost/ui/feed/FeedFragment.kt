package com.example.nanopost.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nanopost.NavGraphDirections
import com.example.nanopost.ui.shared.PostAdapter
import com.example.nanopost.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding
    private val postAdapter = PostAdapter()
    private val viewModel by viewModels<FeedViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceSaved: Bundle?,
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFeed()

        binding.feedRecycler.apply {
            adapter = postAdapter.apply {
                setOnPostClick(
                    callBack = { post ->
                        val action = FeedFragmentDirections.actionGlobalPost(
                            post.id
                        )

                        findNavController().navigate(action)
                    }
                )

                setOnImageClick(
                    callBack = {
                        val action = NavGraphDirections.actionGlobalImage(it.id)
                        findNavController().navigate(action)
                    }
                )
            }
        }

        binding.fab.setOnClickListener {
            val action = NavGraphDirections.actionGlobalCreatePost()
            findNavController().navigate(action)
        }


        viewModel.postsLiveData.observe(viewLifecycleOwner) {
            postAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}

