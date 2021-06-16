package com.example.librarymanagementsystem.ui.book

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.databinding.ItemBookBinding
import com.example.librarymanagementsystem.util.HttpUtil

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
        val book = mList[position]
        itemDataBinding.model = book
        Glide.with(mContext)
            .load("${HttpUtil.LocalAddress}/${book.picture}")
            .into(itemDataBinding.bookView)

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailActivity::class.java)
            intent.putExtra("book", book)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount() = mList.size

    fun setmList(commentList: List<Book>) {
        mList = commentList
    }

}