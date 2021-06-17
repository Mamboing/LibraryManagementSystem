package com.example.librarymanagementsystem.ui.user.data

import com.example.librarymanagementsystem.ui.login.LoggedInUserView

class UserData {
    lateinit var mID: String
    lateinit var mName: String
    lateinit var mType: String

    private fun getUserID(model: LoggedInUserView): String {
        return model.displayName
    }

    private fun getUserName(): String = mName
    private fun getUserType(): String = mType
}