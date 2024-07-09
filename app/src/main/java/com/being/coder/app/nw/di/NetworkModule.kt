package com.being.coder.app.nw.di

import com.being.coder.app.nw.data.repository.impl.CurrentConditionRepositoryImpl
import com.being.coder.app.nw.data.service.WeatherApiService
import com.being.coder.app.nw.domain.repository.CurrentConditionRepository
import com.being.coder.app.nw.domain.usecase.GetCurrentConditionUseCase
import com.being.coder.app.nw.util.K
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideApi(
        builder: Retrofit.Builder,
    ): WeatherApiService {
        return builder
            .build()
            .create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(K.API_BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))

    }

    @Provides
    @Singleton
    fun providesCurrentConditionRepository(
        apiService: WeatherApiService
    ): CurrentConditionRepository {
        return CurrentConditionRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesCurrentConditionUseCase(
        repository: CurrentConditionRepository
    ): GetCurrentConditionUseCase {
        return GetCurrentConditionUseCase(repository)
    }

}