package com.sunmeng.aackotlin.ui

import android.app.Activity
import android.widget.Toast
import com.sunmeng.aackotlin.BuildConfig
import com.sunmeng.aackotlin.data.remote.exception.EmptyException
import com.sunmeng.aackotlin.ui.annotation.ToastType
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by sunmeng on 2017/12/28.
 * Email:sunmeng995@gmail.com
 * Description:基础扩展
 */

fun Activity.toast(msg: CharSequence, @ToastType type: Int = ToastType.NORMAL) {
    showSneaker(this, msg, type)
}

fun Activity.dispatchFailure(error: Throwable) {
    if (BuildConfig.DEBUG) {
        error.printStackTrace()
    }
    if (error !is EmptyException) {
        error.message?.let { toast(it, ToastType.ERROR) }
    } else if (error is SocketTimeoutException) {
        error.message?.let { toast("Network connection timeout", ToastType.ERROR) }
    } else if (error is UnknownHostException || error is ConnectException) {
        error.message?.let { toast("Network unconnected", ToastType.ERROR) }
    }
}