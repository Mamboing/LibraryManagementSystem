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
    private val mSearchCondition = MutableLiveData<String>()
    private val mPref = MutableLiveData<SharedPreferences>()
    private val mBooks = MutableLiveData<List<Book>>()

    fun getMSearchCondition(): LiveData<String> = mSearchCondition

    fun getSearchCondition() = mSearchCondition.value

    fun setSearchCondition(condition: String) {
        mSearchCondition.value = condition
    }

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
        val typeString = if (condition == null || mSearchType.value == null) {
            ""
        } else if (mSearchType.value == SearchType.TAG){
            "?tag=$condition"
        } else {
            "?name=$condition"
        }

        val address: String = HttpUtil.LocalAddress + "/search" + typeString

        LogUtil.e("BookViewModel", address)

//        mBooks.postValue(listOf(
//            Book(
//                222, "c", "John", "a", "123",
//                "AmyP", "2020-11-02", "1111", true, "213121", "A", "1231321"
//            ),
//
//            Book(
//                223, "c", "John", "a", "123",
//                "AmyP", "2020-11-02", "1111", true, "213121", "A", "1231321"
//            )
//        ))

        HttpUtil.getHttp(address, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                mMessage.postValue("???????????????????????????????????????")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData: String = response.body?.string()!!
                LogUtil.e("HomeViewModel", responseData)
                val books: List<Book> = Utility.handleBookList(responseData)
                mBooks.postValue(books)
            }
        })
    }


}