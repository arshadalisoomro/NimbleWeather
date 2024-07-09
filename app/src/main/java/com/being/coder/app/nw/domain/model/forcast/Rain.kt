package com.being.coder.app.nw.domain.model.forcast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    @SerialName("3h")
    val h: Double
)