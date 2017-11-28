package com.sunmeng.aackotlin.data.local

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sunmeng.aackotlin.data.DataSource
import com.sunmeng.aackotlin.data.local.db.AppDatabaseManager
import com.sunmeng.aackotlin.data.remote.RemoteDataSource
import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class LocalDataSource : DataSource {

    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(): LocalDataSource? {
            if (INSTANCE == null) {
                synchronized(LocalDataSource::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = LocalDataSource()
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getGirlList(index: Int): MutableLiveData<GirlData<List<Girl>>>? {
        return AppDatabaseManager.getInstance()?.loadGirlList()
    }

    override fun isLoadingGirlList(): MutableLiveData<Boolean>? {
        return AppDatabaseManager.getInstance()?.isLoadingGirlList()
    }
}