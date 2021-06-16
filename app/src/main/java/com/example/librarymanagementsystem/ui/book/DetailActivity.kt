package com.example.librarymanagementsystem.ui.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.databinding.ActivityDetailBinding
import com.example.librarymanagementsystem.util.HttpUtil

class DetailActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setContentView(dataBinding.root)

        val book = intent.getSerializableExtra("book")!! as Book
        dataBinding.model = book

        Glide.with(this)
            .load("${HttpUtil.LocalAddress}/${book.picture}")
            .into(dataBinding.picture)
    }
}