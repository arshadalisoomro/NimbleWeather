package com.being.coder.app.nw.data.service

import com.being.coder.app.nw.domain.model.current.CurrentCondition
import com.being.coder.app.nw.domain.model.forcast.Forecast
import com.being.coder.app.nw.util.ApiParameters
import com.being.coder.app.nw.util.K
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    // https://api.openweathermap.org/data/2.5/weather?q=sukkur,pk&appid=5e53ab67bc6562d242c771b6d9d597dc
    @GET(K.CURRENT_WEATHER_URL)
    suspend fun getCurrentWeather(
        @Query(ApiParameters.Q) city: String,
        @Query(ApiParameters.APP_ID) appId: String = K.API_KEY,
        @Query(ApiParameters.UNITS) units: String = K.METRIC
    ): CurrentCondition
    // https://api.openweathermap.org/data/2.5/forecast?q=sukkur,pk&appid=5e53ab67bc6562d242c771b6d9d597dc
    suspend fun getForecast(
        @Query(ApiParameters.Q) city: String,
        @Query(ApiParameters.APP_ID) appId: String = K.API_KEY,
        @Query(ApiParameters.UNITS) units: String = K.METRIC
    ) : Forecast

//    suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentCondition
//    suspend fun getForecast(lat: Double, lon: Double): Forecast

}