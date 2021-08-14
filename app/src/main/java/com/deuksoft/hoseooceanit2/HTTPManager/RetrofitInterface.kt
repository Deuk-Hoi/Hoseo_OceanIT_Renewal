package com.deuksoft.hoseooceanit2.HTTPManager

import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.MemberDTO
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {
    @POST("/members/android/memberALL")
    fun getMember(): Call<List<MemberDTO>>

    /*@GET("/research/android/results")
    fun getResearch(@Query("querys") querys: String): Call*/
}