package com.sunmeng.aackotlin.data

import android.app.Application
import com.sunmeng.aackotlin.data.local.LocalDataSource
import com.sunmeng.aackotlin.data.remote.RemoteDataSource

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class Injection {

    companion object {
        fun getDataRepository(application: Application): DataRepository? {
            return DataRepository.getInstance(RemoteDataSource.getInstance()!!, LocalDataSource.getInstance()!!, application)
        }
    }
}