package com.lanier.wanandroid.logic.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object APIService {

    lateinit var api: WanAndroidAPI

    fun init() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create()
    }
}