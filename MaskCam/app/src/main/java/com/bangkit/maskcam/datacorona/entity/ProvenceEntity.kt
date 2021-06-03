package com.bangkit.maskcam.datacorona.entity

import com.google.gson.annotations.SerializedName

data class ProvenceEntity(
    @SerializedName("Kode_Provi")
    val codeProv: Int,
    val Provinsi: String,
    @SerializedName("Kasus_Posi")
    val provPosi: Int,
    @SerializedName("Kasus_Semb")
    val provSemb: Int,
    @SerializedName("Kasus_Meni")
    val provMeni: Int,
)
