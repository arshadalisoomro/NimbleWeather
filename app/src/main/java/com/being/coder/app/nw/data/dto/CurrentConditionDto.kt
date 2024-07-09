package com.being.coder.app.nw.data.dto

import com.being.coder.app.nw.util.WeatherInfoItem

data class CurrentConditionDto(
    val name: String,
    val temp: Double,
    val icon: WeatherInfoItem
)