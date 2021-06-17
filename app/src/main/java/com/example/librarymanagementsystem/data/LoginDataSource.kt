package com.example.librarymanagementsystem.data

import android.os.SystemClock
import com.example.librarymanagementsystem.data.model.LoggedInUser
import com.example.librarymanagementsystem.util.HttpUtil
import com.example.librarymanagementsystem.util.LogUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        var dataCount = "E"
        val address: String = HttpUtil.LocalAddress + "/login?userid=" + username + "&password=" + password
        HttpUtil.getHttp(address, object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                LogUtil.e("LoginResponse", "登录失败，建议检查网络")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()!!
                val responseDataCount = responseData.substring(1, 2)
                dataCount = responseDataCount

                LogUtil.d("LoginResponseCount", responseDataCount)
                LogUtil.d("LoginResponse", responseData)
            }
        })

        // 防止因异步请求造成的dataCount数据冲突
        SystemClock.sleep(2000)

        LogUtil.d("ResponseCount", dataCount)
        return if (dataCount != "E") {
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
            Result.Success(fakeUser)
        } else {
            Result.Error("Error logging in")
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}