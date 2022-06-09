package com.example.nanopost.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nanopost.NavGraphDirections
import com.example.nanopost.R
import com.example.nanopost.data.api.models.CheckUsernameResult
import com.example.nanopost.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceSaved: Bundle?,
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonContinue.setOnClickListener {
            viewModel.checkResult(
                binding.inputUsername.text.toString() ,
                binding.inputPassword.text.toString()
            )
        }

        viewModel.checkUsernameResultLiveData.observe(viewLifecycleOwner) {
            when (it) {
                CheckUsernameResult.TooShort -> binding.username.error =
                    getString(R.string.error_min)
                CheckUsernameResult.TooLong -> binding.username.error =
                    getString(R.string.error_max)
                CheckUsernameResult.InvalidCharacters -> binding.username.error =
                    getString(R.string.error_invalid)
                CheckUsernameResult.Taken, CheckUsernameResult.Free -> {
                    binding.username.isEnabled = false
                    binding.password.isVisible = true
                    binding.username.error = null
                }
                null -> throw IllegalStateException()

            }
        }

        viewModel.navigateLiveData.observe(viewLifecycleOwner) {
            val action = AuthFragmentDirections.actionAuthFragmentToFeedFragment()
            findNavController().navigate(action)
        }
    }
}