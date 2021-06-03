package com.bangkit.maskcam.api

import com.bangkit.maskcam.datacorona.entity.CoronaWorld
import com.bangkit.maskcam.datacorona.entity.CoronaWorldEntity
import com.bangkit.maskcam.datacorona.entity.IndonesiaEntity
import com.bangkit.maskcam.datacorona.entity.ProvinceEntity
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("indonesia")
    fun getCoronaIndo() : Call<ArrayList<IndonesiaEntity>>

    @GET("indonesia/provinsi")
    fun getCoronaProv() : Call<ArrayList<ProvinceEntity>>

    @GET("positif")
    fun getDuniaPositif() : Call<CoronaWorldEntity>

    @GET("/")
    fun getCoronaWorld() : Call<ArrayList<CoronaWorld>>
}