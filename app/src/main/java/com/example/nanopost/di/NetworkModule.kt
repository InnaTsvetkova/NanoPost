package com.example.nanopost.di

import android.util.Log
import com.example.nanopost.data.repository.PrefsRepository
import com.example.nanopost.data.api.ApiService
import com.example.nanopost.data.api.AuthService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @AuthRetrofit
    fun provideAuthRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://nanopost.evolitist.com/api/auth/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideAuthHttpClient(prefsRepository: PrefsRepository): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val token = prefsRepository.getToken() ?: error("Token is null")
                val request = it.request()
                val newRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                it.proceed(newRequest)
            }
            .addInterceptor(HttpLoggingInterceptor { message -> Log.d("OkHttpLogger", message) })
            .build()
    }

    @Provides
    @BaseRetrofit
    fun provideApiRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://nanopost.evolitist.com/api/v1/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideAuthService(@AuthRetrofit retrofit: Retrofit): AuthService {
        return retrofit.create()
    }

    @Provides
    fun provideApiService(@BaseRetrofit retrofit: Retrofit): ApiService {
        return retrofit.create()
    }

}