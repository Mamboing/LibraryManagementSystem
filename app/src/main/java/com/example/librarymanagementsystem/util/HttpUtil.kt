package com.example.librarymanagementsystem.util

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

object HttpUtil {
    const val LocalAddress = "http://192.168.43.111"

    fun getResourceURL(url: String) = "$LocalAddress/resources/$url"

    fun getHttp(address: String, callback: Callback) {
        val client = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }

}