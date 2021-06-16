package com.example.librarymanagementsystem.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.util.LogUtil

class MyNestedScrollBehavior(
    context: Context?,
    attrs: AttributeSet?
) : CoordinatorLayout.Behavior<View>(context, attrs) {

    private var preTopMargin = 1350
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency.id == R.id.appbar
    }

    fun setPreTopMargin(preTopMargin: Int) {
        this.preTopMargin = preTopMargin
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val lp: CoordinatorLayout.LayoutParams =
            child.layoutParams as CoordinatorLayout.LayoutParams
        lp.topMargin = preTopMargin + dependency.top
        child.layoutParams = lp
        return super.onDependentViewChanged(parent, child, dependency)
    }
}