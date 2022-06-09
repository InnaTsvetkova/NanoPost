package com.example.nanopost.domain.usecases

import com.example.nanopost.data.repository.PrefsRepository
import java.lang.IllegalStateException
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
){

    operator fun invoke() : String{
        return prefsRepository.getUserId() ?: throw IllegalStateException("userId is null")
    }
}