package com.lanier.wanandroid.logic.net

import com.lanier.wanandroid.logic.model.HomeDataEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidAPI {

    /**
     * 获取首页数据
     *
     * page: 页数，从0开始
     */
    @GET("/article/list/{page}/json")
    fun getHomeData(@Path("page") page: Int) : Call<HomeDataEntity>

    /**
     * 获取广场数据，从0开始
     */
    @GET("/user_article/list/{page}/json")
    fun getPlazaData(@Path("page") page: Int) : Call<HomeDataEntity>
}