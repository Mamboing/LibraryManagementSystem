package com.example.librarymanagementsystem.ui.book

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.databinding.FragmentBookBinding
import com.example.librarymanagementsystem.ui.book.viewModel.BookViewModel
import com.example.librarymanagementsystem.util.LogUtil

class BookFragment: Fragment() {
    private lateinit var viewModel: BookViewModel
    private lateinit var dataBinding: FragmentBookBinding

    private companion object {
        const val TAG = "BookFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        LogUtil.e(TAG, "BookFragment Created!")

        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.setPref(PreferenceManager.getDefaultSharedPreferences(context))

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false)
        dataBinding.viewModel = viewModel

        val bookAdapter = BookAdapter()

        val recycler = dataBinding.recycler
        recycler.adapter = bookAdapter

        Glide.with(requireContext()).load(R.drawable.home_background).into(dataBinding.picture)

        viewModel.searchBook(null)

        viewModel.getBooks().observe(viewLifecycleOwner) { products ->
            LogUtil.e(TAG, "the size of books is ${products.size}")
            bookAdapter.setmList(products)
            bookAdapter.notifyDataSetChanged()
        }

        viewModel.mSearchCondition.observe(viewLifecycleOwner) { condition ->
            LogUtil.e("$TAG:searchCondition", condition)
            viewModel.searchBook(condition)
        }

        return dataBinding.root
    }
}