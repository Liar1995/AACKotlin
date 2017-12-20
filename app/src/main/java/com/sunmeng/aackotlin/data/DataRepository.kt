package com.sunmeng.aackotlin.data

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sunmeng.aackotlin.data.local.LocalDataSource
import com.sunmeng.aackotlin.data.remote.RemoteDataSource
import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl
import com.sunmeng.aackotlin.utils.Util


/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class DataRepository(private var mRemoteDataSource: RemoteDataSource?, private var mLocalDataSource: LocalDataSource?, private var sApplication: Application) {


    companion object {
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: DataRepository? = null
        fun getInstance(mRemoteDataSource: RemoteDataSource, mLocalDataSource: LocalDataSource, sApplication: Application): DataRepository? {
            if (INSTANCE == null) {
                synchronized(DataRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = DataRepository(mRemoteDataSource, mLocalDataSource, sApplication)
                    }
                }
            }
            return INSTANCE
        }
    }

    fun getGirlList(index: Int): MutableLiveData<GirlData<List<Girl>>>? {
        return if (Util.isNetworkConnected(sApplication))
            mRemoteDataSource!!.getGirlList(index)
        else
            mLocalDataSource!!.getGirlList(index)
    }

    fun isLoadingGirlList(): MutableLiveData<Boolean>? {
        return if (Util.isNetworkConnected(sApplication))
            mRemoteDataSource!!.isLoadingGirlList()
        else
            mLocalDataSource!!.isLoadingGirlList()
    }
}