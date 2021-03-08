package com.example.postapp.network


import com.example.postapp.util.Common.Companion.url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val controller : Controller by lazy {
        retrofit.create(Controller::class.java)
    }
}