package com.lanier.wanandroid.logic.net

import com.lanier.wanandroid.logic.model.HomeDataEntity
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WanAndroidAPI {

    /**
     * 获取首页数据
     *
     * page: 页数，从0开始
     */
    @GET("/article/list/{page}/json")
    fun getHomeData(@Path("page") page: Int): Call<HomeDataEntity>

    /**
     * 获取广场数据，从0开始
     */
    @GET("/user_article/list/{page}/json")
    fun getPlazaData(@Path("page") page: Int): Call<HomeDataEntity>

    /**
     * 搜索，从0开始
     *
     * keyword：关键字，即搜索内容
     */
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    fun search(@Path("page") page: Int, @Field("k") keyword: String): Call<HomeDataEntity>
}