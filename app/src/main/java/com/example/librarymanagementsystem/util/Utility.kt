package com.example.librarymanagementsystem.util

import android.content.Context
import android.text.TextUtils
import com.example.librarymanagementsystem.data.model.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object Utility {
//    const val ERROR_CODE = "-1"
//    const val TRUE_CODE = "000"
//    const val CODE_500 = "500"

//    //检查responseData的code是否为000，若不是则返回问题所在
//    fun checkResponse(response: String?): String {
//        val code = checkString(response, "code")
//            ?: return "后台code为null"
//        if (code == TRUE_CODE) {
//            return TRUE_CODE
//        }
//        if (code == ERROR_CODE) {
//            return "数据返回格式有误"
//        }
//        if (code == CODE_500) {
//            val msg = checkString(response, "msg")
//                ?: return "code:500,msg为null"
//            return if (msg == ERROR_CODE) {
//                "code:500,msg解析错误"
//            } else "code:500, msg:$msg"
//        }
//        return "code:$code"
//    }
//
//    //返回responseData的code是否为000，若不是则Toast接口名称和问题所在
//    fun checkResponse(response: String, address: String): Boolean {
//        val result = checkResponse(response)
//        val interfaceName = address.split(HttpUtil.LocalAddress).toTypedArray()[1]
//        if (result == TRUE_CODE) {
//            return true
//        }
//        LogUtil.e("Utility", "interfaceName: $interfaceName")
//        LogUtil.e("Utility", "result: $result")
//        LogUtil.e("Utility", "response:$response")
//        return false
//    }

    //返回Json数据的特定String值
//    fun checkString(response: String?, string: String?): String {
//        try {
//            val dataObject = JSONObject(response)
//            return dataObject.getString(string)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        return ERROR_CODE
//    }

    fun handleBookList(response: String): List<Book> {
        if (!TextUtils.isEmpty(response)) {
            try {
                val dataArray = JSONArray(response)
                val bookJson = dataArray.toString()
                return Gson().fromJson(bookJson,
                    object : TypeToken<List<Book?>?>() {}.type
                )
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return listOf(
            Book(
                0, "", "", "", "",
                "", "", "", "不存在", "", "", ""
            )
        )
    }
}