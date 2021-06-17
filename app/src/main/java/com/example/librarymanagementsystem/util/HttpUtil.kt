package com.example.librarymanagementsystem.util

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

object HttpUtil {
    const val LocalAddress = "http://155.94.228.62:49373"

    fun getHttp(address: String, callback: Callback) {
        val client = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }

}