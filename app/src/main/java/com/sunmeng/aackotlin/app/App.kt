package com.sunmeng.aackotlin.app

import android.app.Application

/**
 * Created by sunmeng on 2017/11/23.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: App? = null
            private set
    }

}