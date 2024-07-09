package com.being.coder.app.nw.util

import androidx.annotation.DrawableRes
import com.being.coder.app.nw.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Util {
    private val DIRECTIONS = listOf(
        "North",
        "North East",
        "East",
        "South East",
        "South",
        "South West",
        "West",
        "North West"
    )


    fun formatUnixDate(pattern: String, time: Long): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(time * 1000)
    }

    fun formatNormalDate(pattern: String, time: Long): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(time)
    }

    fun getWindDirection(windDirection: Double): String {
        return DIRECTIONS[(windDirection % 360 / 45 % 8).toInt()]
    }

    fun getWeatherInfo(code: Int): WeatherInfoItem {
        return when (code) {
            200, 201, 202, 210, 211, 212, 221, 230, 231, 232 -> WeatherInfoItem("Thunderstorm", R.drawable.scattered_thunderstorms)
            300, 301, 302, 310, 311, 312, 313, 314, 321 -> WeatherInfoItem("Drizzle", R.drawable.rainy_4)
            500, 501, 502, 503, 504, 511, 520, 521, 522, 531 -> WeatherInfoItem("Rain", R.drawable.rainy_5)
            600, 601, 602, 611, 612, 613, 615, 616, 620, 621, 622 -> WeatherInfoItem("Snow", R.drawable.snowy_4)
            701, 711, 721, 731, 741, 751, 761, 762, 771, 781 -> WeatherInfoItem("Fog", R.drawable.fog)
            800 -> WeatherInfoItem("Clear sky", R.drawable.day)
            801 -> WeatherInfoItem("Mainly clear", R.drawable.day)
            802 -> WeatherInfoItem("partly cloudy", R.drawable.cloudy_day_3)
            803, 804 -> WeatherInfoItem("overcast", R.drawable.cloudy_day_3)
            else -> WeatherInfoItem("Unknown", R.drawable.day)
        }
    }

//    fun getWeatherInfo(code: Int): WeatherInfoItem {
//        return when (code) {
//            0 -> WeatherInfoItem("Clear sky", R.drawable.day)
//            1 -> WeatherInfoItem("Mainly clear", R.drawable.day)
//            2 -> WeatherInfoItem("partly cloudy", R.drawable.day)
//            3 -> WeatherInfoItem("overcast", R.drawable.cloudy_day_3)
//            45, 48 -> WeatherInfoItem("Fog", R.drawable.fog)
//            51, 53, 55,
//            -> WeatherInfoItem("Drizzle", R.drawable.rainy_4)
//
//            56, 57 -> WeatherInfoItem("Freezing Drizzle", R.drawable.snowy_4)
//            61,
//            -> WeatherInfoItem("Rain: Slight", R.drawable.rainy_5)
//
//            63 -> WeatherInfoItem("Rain: Moderate", R.drawable.rainy_5)
//            65 -> WeatherInfoItem("Rain: Heavy", R.drawable.rainy_7)
//            66, 67 -> WeatherInfoItem("Freezing Rain", R.drawable.snowy_6)
//            71 -> WeatherInfoItem("Snow fall: Slight", R.drawable.snowy_4)
//            73 -> WeatherInfoItem("Snow fall: moderate", R.drawable.snowy_5)
//            75 -> WeatherInfoItem("Snow fall: Heavy", R.drawable.snowy_6)
//            77 -> WeatherInfoItem("Snow grains", R.drawable.snowy_6)
//            80, 81, 82 -> WeatherInfoItem("Rain showers: Slight", R.drawable.rainy_5)
//            85, 86 -> WeatherInfoItem("Snow showers slight", R.drawable.rain_and_snow_mix)
//            95, 96, 99 -> WeatherInfoItem("Thunderstorm: Slight", R.drawable.scattered_thunderstorms)
//            else -> WeatherInfoItem("Unknown", R.drawable.day)
//        }
//    }

    fun isTodayDate(day: String): Boolean {
        val todayDate = formatNormalDate("E", Date().time)
        return todayDate.lowercase() == day.lowercase()
    }

}

data class WeatherInfoItem(
    val info: String,
    @DrawableRes val icon: Int
)