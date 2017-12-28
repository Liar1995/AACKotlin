package com.sunmeng.aackotlin.ui

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.irozon.sneaker.Sneaker
import com.sunmeng.aackotlin.R
import com.sunmeng.aackotlin.ui.activity.GirlActivity
import com.sunmeng.aackotlin.ui.annotation.ToastType

/**
 * Created by sunmeng on 27/12/2017.
 * Email:sunmeng995@gmail.com
 * Description: 包级函数
 */

fun startGirlActivity(context: Activity, url: String, id: String, view: View) {
    val intent = Intent(context, GirlActivity::class.java)
    intent.putExtra("imageUrl", url)
    intent.putExtra("imageId", id)
    val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, view, id)
    context.startActivity(intent,compat.toBundle())
}

fun showSneaker(context: Activity, msg: CharSequence,  @ToastType type: Int = ToastType.NORMAL) {
    when (type) {
        ToastType.WARNING ->
            Sneaker.with(context)
                    .setTitle("Warning !!")
                    .setMessage(msg.toString())
                    .setIcon(R.drawable.ic_warning)
                    .sneak(R.color.alert_color_warning)
        ToastType.ERROR ->
            Sneaker.with(context)
                    .setTitle("Error !!")
                    .setMessage(msg.toString())
                    .setIcon(R.drawable.ic_error)
                    .sneak(R.color.alert_color_error)
        ToastType.NORMAL ->
            Sneaker.with(context)
                    .setTitle("Normal !!")
                    .setMessage(msg.toString())
                    .setIcon(R.drawable.ic_error)
                    .sneak(R.color.alert_color_normal)
        ToastType.SUCCESS ->
            Sneaker.with(context)
                    .setTitle("Success !!")
                    .setMessage(msg.toString())
                    .setIcon(R.drawable.ic_success)
                    .sneak(R.color.alert_color_success)
    }
}