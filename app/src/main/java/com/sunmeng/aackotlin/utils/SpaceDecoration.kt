package com.sunmeng.aackotlin.utils

import android.graphics.Rect
import android.support.v7.widget.*
import android.view.Gravity
import android.view.View

/**
 * Created by sunmeng on 2017/12/20.
 * Email:sunmeng995@gmail.com
 * Description:RecyclerView SpaceDecoration
 */
class SpaceDecoration(space: Int) : RecyclerView.ItemDecoration() {

    private var halfSpace: Int = 0
    private var mPaddingEdgeSide = true
    private var mPaddingStart = true
    private var mPaddingHeaderFooter = false

    init {
        this.halfSpace = space / 2
    }


    fun setPaddingEdgeSide(mPaddingEdgeSide: Boolean) {
        this.mPaddingEdgeSide = mPaddingEdgeSide
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)
        var spanCount = 0
        var orientation = 0
        var spanIndex = 0
        var headerCount = 0
        var footerCount = 0

        val layoutManager = parent.layoutManager
        if (layoutManager is StaggeredGridLayoutManager) {
            orientation = layoutManager.orientation
            spanCount = layoutManager.spanCount
            spanIndex = (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
        } else if (layoutManager is GridLayoutManager) {
            orientation = layoutManager.orientation
            spanCount = layoutManager.spanCount
            spanIndex = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
        } else if (layoutManager is LinearLayoutManager) {
            orientation = layoutManager.orientation
            spanCount = 1
            spanIndex = 0
        }

        /**
         * 普通Item的尺寸
         */
        if (position >= headerCount && position < parent.adapter.itemCount - footerCount) {
            val gravity: Int
            if (spanIndex == 0 && spanCount > 1)
                gravity = Gravity.LEFT
            else if (spanIndex == spanCount - 1 && spanCount > 1)
                gravity = Gravity.RIGHT
            else if (spanCount == 1)
                gravity = Gravity.FILL_HORIZONTAL
            else {
                gravity = Gravity.CENTER
            }
            if (orientation == OrientationHelper.VERTICAL) {
                when (gravity) {
                    Gravity.LEFT -> {
                        if (mPaddingEdgeSide)
                            outRect.left = halfSpace * 2
                        outRect.right = halfSpace
                    }
                    Gravity.RIGHT -> {
                        outRect.left = halfSpace
                        if (mPaddingEdgeSide)
                            outRect.right = halfSpace * 2
                    }
                    Gravity.FILL_HORIZONTAL -> if (mPaddingEdgeSide) {
                        outRect.left = halfSpace * 2
                        outRect.right = halfSpace * 2
                    }
                    Gravity.CENTER -> {
                        outRect.left = halfSpace
                        outRect.right = halfSpace
                    }
                }
                if (position - headerCount < spanCount && mPaddingStart) outRect.top = halfSpace * 2
                outRect.bottom = halfSpace * 2
            } else {
                when (gravity) {
                    Gravity.LEFT -> {
                        if (mPaddingEdgeSide)
                            outRect.bottom = halfSpace * 2
                        outRect.top = halfSpace
                    }
                    Gravity.RIGHT -> {
                        outRect.bottom = halfSpace
                        if (mPaddingEdgeSide)
                            outRect.top = halfSpace * 2
                    }
                    Gravity.FILL_HORIZONTAL -> if (mPaddingEdgeSide) {
                        outRect.left = halfSpace * 2
                        outRect.right = halfSpace * 2
                    }
                    Gravity.CENTER -> {
                        outRect.bottom = halfSpace
                        outRect.top = halfSpace
                    }
                }
                if (position - headerCount < spanCount && mPaddingStart) outRect.left = halfSpace * 2
                outRect.right = halfSpace * 2
            }
        } else {//只有HeaderFooter进到这里
            if (mPaddingHeaderFooter) {//并且需要padding Header&Footer
                if (orientation == OrientationHelper.VERTICAL) {
                    if (mPaddingEdgeSide) {
                        outRect.left = halfSpace * 2
                        outRect.right = halfSpace * 2
                    }
                    outRect.top = halfSpace * 2
                } else {
                    if (mPaddingEdgeSide) {
                        outRect.top = halfSpace * 2
                        outRect.bottom = halfSpace * 2
                    }
                    outRect.left = halfSpace * 2
                }
            }
        }
    }

}