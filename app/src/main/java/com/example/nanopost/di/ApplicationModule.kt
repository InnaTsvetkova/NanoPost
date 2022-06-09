package com.example.nanopost.di

import android.content.ContentResolver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun providePrefs(@ApplicationContext context: Context) : SharedPreferences{
        return context.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    fun provideContentResolver(@ApplicationContext context: Context) : ContentResolver{
        return context.contentResolver
    }
}