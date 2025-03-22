package com.lanier.wanandroid.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lanier.wanandroid.logic.model.HomeDataEntity
import com.lanier.wanandroid.logic.model.HomeRealDataX
import com.lanier.wanandroid.logic.net.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val homeDataLiveData = MutableLiveData<List<HomeRealDataX>>()

    private var page = 0

    var isRefresh: Boolean = false
        private set

    fun showData(refresh: Boolean = false) {
        isRefresh = refresh
        if (refresh) {
            page = 0
        }
        APIService.api.getHomeData(page)
            .enqueue(object : Callback<HomeDataEntity> {
                override fun onResponse(
                    p0: Call<HomeDataEntity?>,
                    p1: Response<HomeDataEntity?>
                ) {
                    val origin = p1.body()
                    if (origin != null) {
                        homeDataLiveData.postValue(origin.data.datas)
                        val hasNext = origin.data.offset <= origin.data.total
                        if (hasNext) page++
                    }
                }

                override fun onFailure(
                    p0: Call<HomeDataEntity?>,
                    p1: Throwable
                ) {
                }
            })
    }

    fun showPlazaData(refresh: Boolean = false) {
        isRefresh = refresh
        if (refresh) {
            page = 0
        }
        APIService.api.getPlazaData(page)
            .enqueue(object : Callback<HomeDataEntity> {
                override fun onResponse(
                    p0: Call<HomeDataEntity?>,
                    p1: Response<HomeDataEntity?>
                ) {
                    val origin = p1.body()
                    if (origin != null) {
                        homeDataLiveData.postValue(origin.data.datas)
                        val hasNext = origin.data.offset <= origin.data.total
                        if (hasNext) page++
                    }
                }

                override fun onFailure(
                    p0: Call<HomeDataEntity?>,
                    p1: Throwable
                ) {
                }
            })
    }
}