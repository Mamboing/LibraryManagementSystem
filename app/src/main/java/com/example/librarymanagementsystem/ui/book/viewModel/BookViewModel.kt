package com.example.librarymanagementsystem.ui.book.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.model.SearchType
import com.example.librarymanagementsystem.util.HttpUtil
import com.example.librarymanagementsystem.util.LogUtil
import com.example.librarymanagementsystem.util.Utility
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class BookViewModel : ViewModel() {
    private val mMessage = MutableLiveData<String>()
    val mSearchType = MutableLiveData<SearchType>()
    val mSearchCondition = MutableLiveData<String>()
    private val mPref = MutableLiveData<SharedPreferences>()
    private val mBooks = MutableLiveData<List<Book>>()

    fun getMessage(): LiveData<String> = mMessage

    fun setMessage(message: String) {
        this.mMessage.value = message
    }

    fun getPref(): LiveData<SharedPreferences> = mPref

    fun setPref(pref: SharedPreferences) {
        this.mPref.value = pref
    }

    fun getBooks(): LiveData<List<Book>> = mBooks

    fun searchBook(condition: String?) {
        val address: String = HttpUtil.LocalAddress + "/search"
        mBooks.postValue(listOf(
            Book(
                222, "c", "John", "a", "123",
                "AmyP", "2020-11-02", "1111", true, "213121", "A", "1231321"
            ),

            Book(
                223, "c", "John", "a", "123",
                "AmyP", "2020-11-02", "1111", true, "213121", "A", "1231321"
            )
        ))

//        HttpUtil.getHttp(address, object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//                mMessage.postValue("获取精选商品失败，建议检查网络")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val responseData: String = response.body?.string()!!
//                LogUtil.e("HomeViewModel", responseData)
//                if (Utility.checkResponse(responseData, address)) {
//                    val books: List<Book> = Utility.handleBookList(responseData)
//                    mBooks.postValue(books)
//                }
//            }
//        })
    }


}