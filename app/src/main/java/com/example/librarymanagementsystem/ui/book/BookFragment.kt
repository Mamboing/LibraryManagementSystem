package com.example.librarymanagementsystem.ui.book

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.SearchType
import com.example.librarymanagementsystem.databinding.DialogScreenBinding
import com.example.librarymanagementsystem.databinding.FragmentBookBinding
import com.example.librarymanagementsystem.ui.book.viewModel.BookViewModel
import com.example.librarymanagementsystem.util.LogUtil

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
            dataBinding.recycler.adapter!!.notifyDataSetChanged()
        }

        viewModel.getMSearchCondition().observe(viewLifecycleOwner) { condition ->
            LogUtil.e("$TAG:searchCondition", condition)
            viewModel.searchBook(condition)
        }

        viewModel.getMessage().observe(viewLifecycleOwner) { message ->
            LogUtil.e("$TAG:message", message)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        dataBinding.text.setOnClickListener {
            createDialogScreen(it)
        }

        val editText = dataBinding.searchEdittext

        editText.setOnEditorActionListener { v, actionId, event ->
            val manager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            LogUtil.e(TAG, actionId.toString())
            if (actionId == KeyEvent.KEYCODE_HOME || actionId == KeyEvent.KEYCODE_ENTER) {
                LogUtil.e(TAG, "Entered")

                if (manager.isActive) {
                    manager.hideSoftInputFromWindow(
                            editText.applicationWindowToken, 0
                    )
                    viewModel.setSearchCondition(editText.text.toString())
                }
            }
            false
        }

        return dataBinding.root
    }

    fun createDialogScreen(view: View?) {
        LogUtil.e(TAG, "dialog should be created")
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
                R.id.by_name -> viewModel.mSearchType.value = SearchType.NAME
                else -> viewModel.mSearchType.value = SearchType.TAG
            }
            LogUtil.e("buttonGroup", buttonGroup.checkedRadioButtonId.toString())
            dialog.dismiss()
        }

        dialog.show()
        val dialogWindow: Window = dialog.window!!
        val lp = dialogWindow.attributes
        lp.width = (requireActivity().windowManager.defaultDisplay.width * 0.7).toInt()
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialogWindow.attributes = lp
    }

}