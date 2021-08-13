package com.deuksoft.hoseooceanit2.HTTPManager

import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.MemberDTO
import retrofit2.Call
import retrofit2.http.POST

interface RetrofitInterface {
    @POST("/members/android/memberALL")
    fun getMember(): Call<List<MemberDTO>>
}