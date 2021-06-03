package com.bangkit.maskcam.datacorona.entity

import com.google.gson.annotations.SerializedName

data class Corona(
    @SerializedName("Country_Region")
    val region: String,
    val Confirmed: Int,
    val Deaths: Int,
    val Recovered: Int
)
