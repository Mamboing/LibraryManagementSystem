package com.example.librarymanagementsystem.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.librarymanagementsystem.R


class UserFragment : Fragment() {
    private lateinit var mID: String
    private lateinit var mName: String
    private lateinit var mType: String

    // 数据对应TextView的app:edt_content
    fun getUserID(): String = mID
    fun getUserName(): String = mName
    fun getUserType(): String = mType

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }
}