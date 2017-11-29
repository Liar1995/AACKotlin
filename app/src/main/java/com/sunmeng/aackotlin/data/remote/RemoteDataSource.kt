package com.sunmeng.aackotlin.data.remote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sunmeng.aackotlin.common.convert.LiveDataObservableAdapter
import com.sunmeng.aackotlin.data.DataSource
import com.sunmeng.aackotlin.data.local.db.AppDatabaseManager
import com.sunmeng.aackotlin.data.remote.api.ApiGirl
import com.sunmeng.aackotlin.data.remote.api.ApiManager
import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class RemoteDataSource : DataSource {

    private var mIsLoadingGirlList: MutableLiveData<Boolean>? = null

    private var mGirlList: MutableLiveData<GirlData<List<Girl>>>? = null

    private var mApiGirl: ApiGirl? = null

    init {
        mIsLoadingGirlList = MutableLiveData()
        mGirlList = MutableLiveData()
        mApiGirl = ApiManager.getInstance()?.getApiGirl()
    }


    companion object {
        private var INSTANCE: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource? {
            if (INSTANCE == null) {
                synchronized(RemoteDataSource::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = RemoteDataSource()
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getGirlList(index: Int): MutableLiveData<GirlData<List<Girl>>>? {
        mIsLoadingGirlList?.value = true
        mApiGirl?.getGirlsDataCall(index)?.enqueue(object : Callback<GirlData<List<Girl>>> {
            override fun onResponse(call: Call<GirlData<List<Girl>>>?, response: Response<GirlData<List<Girl>>>?) {
                mIsLoadingGirlList?.value = false
                if (response!!.isSuccessful || response.body()?.error == false) {
                    mGirlList?.value = response.body()
                    refreshLocalGirlList(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<GirlData<List<Girl>>>?, t: Throwable?) {
                mIsLoadingGirlList?.value = false
            }
        })
        return mGirlList
//        return LiveDataObservableAdapter.fromObservable(mApiGirl?.getGirlsData(index)?.subscribeOn(Schedulers.io())
//                ?.observeOn(AndroidSchedulers.mainThread())!!,mIsLoadingGirlList)
    }

    private fun refreshLocalGirlList(results: List<Girl>?) {
        if (results != null) AppDatabaseManager.getInstance()?.insertGirlList(results)
    }

    override fun isLoadingGirlList(): MutableLiveData<Boolean>? {
        return mIsLoadingGirlList
    }

}