package com.being.coder.app.nw.domain.model.current


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("deg")
    val deg: Int,
    @SerialName("speed")
    val speed: Double
)