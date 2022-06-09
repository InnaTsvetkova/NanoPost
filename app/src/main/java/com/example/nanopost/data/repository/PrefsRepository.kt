package com.example.nanopost.data.repository

interface PrefsRepository {
    fun putToken(token: String)

    fun getToken(): String?

    fun putUserId(userId: String)

    fun getUserId(): String?

}