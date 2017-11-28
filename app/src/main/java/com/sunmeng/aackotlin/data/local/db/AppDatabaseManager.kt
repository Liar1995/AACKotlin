package com.sunmeng.aackotlin.data.local.db

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import com.sunmeng.aackotlin.data.remote.RemoteDataSource
import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl
import java.util.ArrayList

/**
 * Created by sunmeng on 2017/11/28.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class AppDatabaseManager {

    val DATABASE_NAME = "architecture-practice-db"
    var mIsLoadingGirlList: MutableLiveData<Boolean>? = null
    var mGirlList: MutableLiveData<GirlData<List<Girl>>>? = null

    var INSTANCE: AppDatabaseManager? = null

    var mDB: AppDatabase? = null

    init {
        mIsLoadingGirlList = MutableLiveData()
        mGirlList = MutableLiveData()
    }

    companion object {
        private var INSTANCE: AppDatabaseManager? = null
        fun getInstance(): AppDatabaseManager? {
            if (INSTANCE == null) {
                synchronized(AppDatabaseManager::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = AppDatabaseManager()
                    }
                }
            }
            return INSTANCE
        }
    }

    @SuppressLint("StaticFieldLeak")
    fun createDB(mContext: Context) {
        object : AsyncTask<Context, Unit, Unit>() {
            override fun doInBackground(vararg p0: Context?) {
                val context = p0[0]?.applicationContext
                mDB = Room.databaseBuilder(context!!,
                        AppDatabase::class.java, DATABASE_NAME).build()
                return Unit
            }
        }.execute(mContext.applicationContext)
    }

    @SuppressLint("StaticFieldLeak")
    fun insertGirlList(girls: List<Girl>) {
        object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg p0: Unit?) {
                mDB?.beginTransaction()
                try {
                    mDB?.girlDao()?.insertGirls(girls)
                    mDB?.setTransactionSuccessful()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    mDB?.endTransaction()
                }
            }

        }.execute()
    }

    @SuppressLint("StaticFieldLeak")
    fun loadGirlList(): MutableLiveData<GirlData<List<Girl>>>? {
        mIsLoadingGirlList?.value = true
        object : AsyncTask<Unit, Unit, GirlData<List<Girl>>>() {
            override fun doInBackground(vararg p0: Unit?): GirlData<List<Girl>> {
                val results = GirlData<List<Girl>>()
                mDB?.beginTransaction()
                try {
                    results.error=false
                    results.results=mDB?.girlDao()?.loadAllGirls()!!
                    mDB?.setTransactionSuccessful()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    mDB?.endTransaction()
                }
                return results
            }

            override fun onPostExecute(result: GirlData<List<Girl>>?) {
                super.onPostExecute(result)
                mIsLoadingGirlList?.value = false
                mGirlList?.value = result
            }

            override fun onCancelled() {
                super.onCancelled()
                mIsLoadingGirlList?.value = false
            }
        }.execute()
        return mGirlList
    }

    fun isLoadingGirlList(): MutableLiveData<Boolean>? {
        return mIsLoadingGirlList
    }
}