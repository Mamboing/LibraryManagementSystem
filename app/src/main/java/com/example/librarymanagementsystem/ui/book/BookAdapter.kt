package com.example.librarymanagementsystem.ui.book

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.databinding.ItemBookBinding

class BookAdapter: RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private lateinit var mContext: Context
    private var mList: List<Book> = ArrayList()
    private lateinit var itemDataBinding: ItemBookBinding

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        itemDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.item_book,
            parent,
            false
        )

        return ViewHolder(itemDataBinding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = mList[position]
        itemDataBinding.model = comment
    }

    override fun getItemCount() = mList.size

    fun setmList(commentList: List<Book>) {
        mList = commentList
    }

}