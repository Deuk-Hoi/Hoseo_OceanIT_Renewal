package com.deuksoft.hoseooceanit2.HTTPManager.RepositoryManager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.FieldDTO
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.ResultDTO
import com.deuksoft.hoseooceanit2.HTTPManager.RetrofitAPI
import com.deuksoft.hoseooceanit2.HTTPManager.RetrofitInterface
import com.deuksoft.hoseooceanit2.HTTPManager.Tools
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ResearchRepository {
    private val retrofit : Retrofit = RetrofitAPI.getInstance(Tools().MAIN_URL)
    private val service = retrofit.create(RetrofitInterface::class.java)
    val message =  MutableLiveData<String>()

    fun getResearch(classify : String):LiveData<List<ResultDTO>>{
        var result = MutableLiveData<List<ResultDTO>>()

        service.getResearch(classify).enqueue(object :Callback<List<ResultDTO>>{
            override fun onResponse(call: Call<List<ResultDTO>>, response: Response<List<ResultDTO>>) {
                result.value = response.body()
            }

            override fun onFailure(call: Call<List<ResultDTO>>, t: Throwable) {
                message.value = "서버와의 통신이 원활하지 않습니다."
            }

        })
        return result
    }

    fun getField(classify: String):LiveData<List<FieldDTO>>{
        var result = MutableLiveData<List<FieldDTO>>()

        service.getField(classify).enqueue(object : Callback<List<FieldDTO>>{
            override fun onResponse(call: Call<List<FieldDTO>>, response: Response<List<FieldDTO>>) {
                result.value = response.body()
            }

            override fun onFailure(call: Call<List<FieldDTO>>, t: Throwable) {
                message.value = "서버와의 통신이 원활하지 않습니다."
            }

        })
        return result
    }
}