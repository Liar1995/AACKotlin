package com.sunmeng.aackotlin.data.local

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sunmeng.aackotlin.data.DataSource
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

    override fun getGirlList(index: Int): LiveData<GirlData<List<Girl>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isLoadingGirlList(): MutableLiveData<Boolean>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}