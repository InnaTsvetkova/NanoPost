package com.example.nanopost.data.repository.impl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.nanopost.data.repository.PrefsRepository
import javax.inject.Inject

class PrefsRepositoryImpl @Inject constructor(
    private val prefs: SharedPreferences
): PrefsRepository {

    companion object{
        private const val KEY_TOKEN = "key token"
        private const val KEY_USERID = "key userId"
    }

    override fun putToken(token: String) {
        prefs.edit {
            putString(KEY_TOKEN, token)
        }
    }

    override fun getToken(): String? {
       return prefs.getString(KEY_TOKEN, null)
    }

    override fun putUserId(userId: String) {
        prefs.edit{
            putString(KEY_USERID, userId)
        }
    }

    override fun getUserId(): String? {
        return prefs.getString(KEY_USERID, null)
    }
}