package com.sunmeng.aackotlin.ui.listener

import android.view.View

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
interface ItemClickPresenter<in Any> {
    fun onItemClick(v: View? = null, item: Any)
}

interface ItemLongClickPresenter<in Any> {
    fun onItemLongClick(v: View? = null, item: Any): Boolean
}