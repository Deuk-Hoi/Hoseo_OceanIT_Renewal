package com.deuksoft.hoseooceanit2.HTTPManager

import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {
    @POST("/members/android/memberALL")
    fun getMember(): Call<List<MemberDTO>>

    @GET("/research/android/results")
    fun getResearch(@Query("classify") querys: String): Call<List<ResultDTO>>

    @GET("/research/android/fields")
    fun getField(@Query("classify") querys: String):Call<List<FieldDTO>>

    @GET("/research/android/")
    fun getLatelyField():Call<LatelyFieldDTO>

    @POST("/auth/android/login")
    fun tryLogin(@Body loginInfo : HashMap<String, String>):Call<UserInfoDTO>
}