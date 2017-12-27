package com.sunmeng.aackotlin.ui

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import com.sunmeng.aackotlin.ui.activity.GirlActivity

/**
 * Created by sunmeng on 27/12/2017.
 * Email:sunmeng995@gmail.com
 * Description:页面跳转
 */

fun startGirlActivity(context: Activity, url: String, id: String, view: View) {
    val intent = Intent(context, GirlActivity::class.java)
    intent.putExtra("imageUrl", url)
    intent.putExtra("imageId", id)
    val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, view, id)
    context.startActivity(intent,compat.toBundle())
}