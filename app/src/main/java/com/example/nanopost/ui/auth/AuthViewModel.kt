package com.example.nanopost.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanopost.data.api.models.CheckUsernameResult
import com.example.nanopost.domain.usecases.CheckUsernameUseCase
import com.example.nanopost.domain.usecases.LoginUseCase
import com.example.nanopost.domain.usecases.RegisterUseCase
import com.example.nanopost.domain.usecases.SaveAuthDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val checkUsernameUseCase: CheckUsernameUseCase,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val saveAuthDataUseCase: SaveAuthDataUseCase
) : ViewModel() {

    val checkUsernameResultLiveData = MutableLiveData<CheckUsernameResult>()
    val navigateLiveData = MutableLiveData<Any>()

    private fun checkUsername(username: String) {
        viewModelScope.launch {
            checkUsernameResultLiveData.value = checkUsernameUseCase.invoke(username)
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            val tokenResponse = loginUseCase.invoke(username, password)
            saveAuthDataUseCase.invoke(tokenResponse)
            navigateLiveData.value = Any()
        }
    }

    private fun register(username: String, password: String) {
        viewModelScope.launch {
            val tokenResponse = registerUseCase.invoke(username, password)
            saveAuthDataUseCase.invoke(tokenResponse)
            navigateLiveData.value = Any()

        }
    }


    fun checkResult(username: String, password: String) {
        val result = checkUsernameResultLiveData.value
        when (result) {
            CheckUsernameResult.TooShort, CheckUsernameResult.TooLong, CheckUsernameResult.InvalidCharacters, null -> checkUsername(
                username
            )
            CheckUsernameResult.Taken -> {
                login(username, password)
            }
            CheckUsernameResult.Free -> register(username, password)

        }
    }
}