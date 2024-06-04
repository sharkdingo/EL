package com.example.el_work.MainUserActivity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.el_work.R

class BitmapItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val dividerWidth: Int
    private val dividerPaint: Paint

    init {
        dividerPaint = Paint()
        dividerPaint.color = ContextCompat.getColor(context, R.color.black) // 设置分隔线颜色
        dividerWidth = 4
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        val childCount = parent.childCount

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val left = child.right + params.rightMargin
            val right = left + dividerWidth
            val top = child.top + params.topMargin
            val bottom = child.bottom - params.bottomMargin

            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), dividerPaint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: android.view.View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = dividerWidth
    }
}