package com.being.coder.app.nw.data.repository.impl

import com.being.coder.app.nw.data.dto.CurrentConditionDto
import com.being.coder.app.nw.data.service.WeatherApiService
import com.being.coder.app.nw.domain.model.current.CurrentCondition
import com.being.coder.app.nw.domain.repository.CurrentConditionRepository
import com.being.coder.app.nw.util.Response
import com.being.coder.app.nw.util.WeatherInfoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentConditionRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
): CurrentConditionRepository {
    override suspend fun getCurrentCondition(
        city: String
    ): Flow<Response<CurrentConditionDto>> = flow {
        // Loading state
        emit(Response.Loading)

        // Get current condition data from API
        val currentConditionData = apiService.getCurrentWeather(city)

        // Convert current condition data to DTO
        val currentConditionDto = currentConditionData.toDto()

        // Success state
        emit(Response.Success(currentConditionDto))
    }.catch { e ->
        // Error state
        e.printStackTrace()
        emit(Response.Error(e.message.orEmpty()))
    }

    private fun CurrentCondition.toDto(): CurrentConditionDto {
        return CurrentConditionDto(
            name = name,
            temp = main.temp,
            icon = WeatherInfoItem(
                weather.first().description,
                weather.first().id
            )
        )
    }
}