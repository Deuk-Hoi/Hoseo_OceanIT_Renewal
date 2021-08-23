package com.deuksoft.hoseooceanit2.HTTPManager.RepositoryManager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.LatelyFieldDTO
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.MemberDTO
import com.deuksoft.hoseooceanit2.HTTPManager.RetrofitAPI
import com.deuksoft.hoseooceanit2.HTTPManager.RetrofitInterface
import com.deuksoft.hoseooceanit2.HTTPManager.Tools
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainRepository {
    private val retrofit : Retrofit = RetrofitAPI.getInstance(Tools().MAIN_URL)
    private val service = retrofit.create(RetrofitInterface::class.java)
    val message =  MutableLiveData<String>()

    fun getLatelyField():LiveData<LatelyFieldDTO>{
        var result = MutableLiveData<LatelyFieldDTO>()
        service.getLatelyField().enqueue(object : Callback<LatelyFieldDTO>{
            override fun onResponse(call: Call<LatelyFieldDTO>, response: Response<LatelyFieldDTO>) {
                result.value = response.body()
            }
            override fun onFailure(call: Call<LatelyFieldDTO>, t: Throwable) {
                message.value = "서버와의 통신이 원활하지 않습니다."
            }

        })
        return result
    }
}