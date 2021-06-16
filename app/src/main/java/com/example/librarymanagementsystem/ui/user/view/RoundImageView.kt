package com.example.librarymanagementsystem.ui.user.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RoundImageView : AppCompatImageView {
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    constructor(context: Context?) : super(context!!) {
        init()
    }

    private val roundRect = RectF()
    private var rect_adius = 90f
    private val maskPaint = Paint()
    private val zonePaint = Paint()
    private fun init() {
        maskPaint.isAntiAlias = true
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        maskPaint.isFilterBitmap = true
        zonePaint.isAntiAlias = true
        zonePaint.color = Color.WHITE
        zonePaint.isFilterBitmap = true
        val density = resources.displayMetrics.density
        rect_adius = rect_adius * density
    }

    fun setRectAdius(adius: Float) {
        rect_adius = adius
        invalidate()
    }

    override fun onLayout(
        changed: Boolean, left: Int, top: Int, right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        val w = width
        val h = height
        roundRect[0f, 0f, w.toFloat()] = h.toFloat()
    }

    override fun draw(canvas: Canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG)
        canvas.drawRoundRect(roundRect, rect_adius, rect_adius, zonePaint)
        //
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG)
        super.draw(canvas)
        canvas.restore()
    }
}