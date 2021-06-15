package com.example.librarymanagementsystem.data.model

data class Book(
    val bookId: Int,
    val name: String,
    val author: String,
    val category: String,
    val ISBN: String,
    val publisher: String,
    val publishTime: String,
    val getId: String, // 索书号
    var state: Boolean, // 馆藏状态
    val summary: String, // 简介
    var tag: String,
    val picture: String
)
