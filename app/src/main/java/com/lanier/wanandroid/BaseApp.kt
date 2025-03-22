package com.lanier.wanandroid

import android.app.Application
import com.lanier.wanandroid.logic.net.APIService

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        APIService.init()
    }
}