package com.lanier.wanandroid.logic.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtils {
    fun convertTimestamp(time: Long): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        val date = Date(time)
        return format.format(date)
    }
}