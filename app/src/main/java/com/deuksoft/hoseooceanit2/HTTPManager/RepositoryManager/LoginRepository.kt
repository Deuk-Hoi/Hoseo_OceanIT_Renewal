package com.deuksoft.hoseooceanit2.HTTPManager.RepositoryManager

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.UserInfoDTO
import com.deuksoft.hoseooceanit2.HTTPManager.RetrofitAPI
import com.deuksoft.hoseooceanit2.HTTPManager.RetrofitInterface
import com.deuksoft.hoseooceanit2.HTTPManager.Tools
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginRepository {
    private val retrofit : Retrofit = RetrofitAPI.getInstance(Tools().MAIN_URL)
    private val service = retrofit.create(RetrofitInterface::class.java)
    val message =  MutableLiveData<String>()

    fun tryLogin(userId : String, userPass: String):LiveData<UserInfoDTO>{
        var result = MutableLiveData<UserInfoDTO>()
        var loginInfo = hashMapOf(
            "userId" to userId,
            "userPw" to userPass
        )
        service.tryLogin(loginInfo).enqueue(object : Callback<UserInfoDTO>{
            override fun onResponse(call: Call<UserInfoDTO>, response: Response<UserInfoDTO>) {
                if(response.code() == 200){
                    result.value = response.body()
                }else{
                    message.value = response.body()!!.message
                }
            }

            override fun onFailure(call: Call<UserInfoDTO>, t: Throwable) {
                message.value = "서버와의 통신이 원활하지 않습니다."
            }

        })
        return result
    }

    fun checkLogin(){

    }

}