package com.example.librarymanagementsystem.data

import com.example.librarymanagementsystem.data.model.LoggedInUser
import com.example.librarymanagementsystem.util.HttpUtil
import com.example.librarymanagementsystem.util.LogUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        val address: String = HttpUtil.LocalAddress + "/login?userid=" + username + "&password=" + password
        HttpUtil.getHttp(address, object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                LogUtil.e("LoginResponse", "登录失败，建议检查网络")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = JSONArray(response).toString()
                LogUtil.d("LoginResponse", responseData)
            }
        })

        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}