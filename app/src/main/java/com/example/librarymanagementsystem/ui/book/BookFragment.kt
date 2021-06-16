package com.example.librarymanagementsystem.ui.book

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.SearchType
import com.example.librarymanagementsystem.databinding.DialogScreenBinding
import com.example.librarymanagementsystem.databinding.FragmentBookBinding
import com.example.librarymanagementsystem.ui.book.viewModel.BookViewModel
import com.example.librarymanagementsystem.util.LogUtil
import java.util.*

class BookFragment: Fragment() {
    private lateinit var viewModel: BookViewModel
    private lateinit var dataBinding: FragmentBookBinding
    private lateinit var dialogScreenBinding: DialogScreenBinding

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

        viewModel.getMessage().observe(viewLifecycleOwner) { message ->
            LogUtil.e("$TAG:message", message)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

//        dataBinding.searchEdittext.setOnLongClickListener {
//            createDialog(it)
//        }

        return dataBinding.root
    }

    fun createDialog(view: View) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        dialogScreenBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(requireContext()),
                R.layout.dialog_screen,
                null,
                false
            )
        dialog.setView(dialogScreenBinding.root)

        val buttonGroup = dialogScreenBinding.group

        dialogScreenBinding.confirm.setOnClickListener {
            when (buttonGroup.checkedRadioButtonId) {
                0 -> viewModel.mSearchType.value = SearchType.NAME
                else -> viewModel.mSearchType.value = SearchType.TAG
            }
        }

    }
}