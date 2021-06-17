package com.example.librarymanagementsystem.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.ui.login.LoggedInUserView
import com.example.librarymanagementsystem.ui.user.view.ItemGroup


class UserFragment : Fragment() {
    private lateinit var mID: String
    private lateinit var mName: String
    private lateinit var mType: String

    private fun getUserID(model: LoggedInUserView) {
        mID = model.displayName
    }
    private fun getUserName(): String = mName
    private fun getUserType(): String = mType

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }
}