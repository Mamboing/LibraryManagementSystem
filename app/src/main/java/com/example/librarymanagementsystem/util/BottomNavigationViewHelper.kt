package com.example.librarymanagementsystem.util

import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

object BottomNavigationViewHelper {
    fun getBottomNavigationViews(view: BottomNavigationView): List<BottomNavigationItemView> {
        val bottomNavigationItemViews: MutableList<BottomNavigationItemView> = ArrayList()
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        for (i in 0 until menuView.childCount) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            bottomNavigationItemViews.add(item)
        }
        return bottomNavigationItemViews
    }
}