package com.being.coder.app.nw.domain.model.forcast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerialName("city")
    val city: City,
    @SerialName("cnt")
    val cnt: Int,
    @SerialName("cod")
    val cod: String,
    @SerialName("list")
    val list: List<ForecastList>,
    @SerialName("message")
    val message: Int
)