package com.deuksoft.hoseooceanit2.HTTPManager

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitAPI {
    private var retrofit : Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    fun getInstance(url : String): Retrofit {
        retrofit = null
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit!!
    }
}