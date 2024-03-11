package com.benki.recipefinder.di

import com.benki.recipefinder.data.constants.NetworkConstants.BASE_URL
import com.benki.recipefinder.network.models.MealApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideMealApiService(retrofit: Retrofit): MealApi {
        return retrofit.create(MealApi::class.java)
    }
}