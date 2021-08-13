package com.deuksoft.hoseooceanit2.HTTPManager.RepositoryManager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.MemberDTO
import com.deuksoft.hoseooceanit2.HTTPManager.RetrofitAPI
import com.deuksoft.hoseooceanit2.HTTPManager.RetrofitInterface
import com.deuksoft.hoseooceanit2.HTTPManager.Tools
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MemberRepository {
    private val retrofit : Retrofit = RetrofitAPI.getInstance(Tools().MAIN_URL)
    private val service = retrofit.create(RetrofitInterface::class.java)
    val message =  MutableLiveData<String>()

    fun getMember():LiveData<List<MemberDTO>>{
        var result = MutableLiveData<List<MemberDTO>>()

        service.getMember().enqueue(object : Callback<List<MemberDTO>>{
            override fun onResponse(call: Call<List<MemberDTO>>, response: Response<List<MemberDTO>>) {
                result.value = response.body()
            }

            override fun onFailure(call: Call<List<MemberDTO>>, t: Throwable) {
                message.value = "서버와의 통신이 원활하지 않습니다."
            }

        })
        return result
    }
}