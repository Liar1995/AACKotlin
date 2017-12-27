package com.sunmeng.aackotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
object Util {

    fun isNetworkConnected(mContext: Context): Boolean {
        val mConnectivityManager: ConnectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mNetworkInfo = mConnectivityManager.activeNetworkInfo
        return mNetworkInfo?.isAvailable ?: return false
    }

    fun showSnackbar(parentView: View, msg: String) {
        if (TextUtils.isEmpty(msg)) return else Snackbar.make(parentView, msg, Snackbar.LENGTH_LONG).show()
    }

}