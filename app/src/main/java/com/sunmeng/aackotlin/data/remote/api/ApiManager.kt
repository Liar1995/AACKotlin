package com.sunmeng.aackotlin.data.remote.api

import com.sunmeng.aackotlin.data.remote.RemoteDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class ApiManager {

    private var sApiGirl: ApiGirl? = null

    companion object {

        private val GIRL_URL = "http://gank.io/"

        private var INSTANCE: ApiManager? = null

        fun getInstance(): ApiManager? {
            if (INSTANCE == null) {
                synchronized(ApiManager::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ApiManager()
                    }
                }
            }
            return INSTANCE
        }
    }

    fun getApiGirl(): ApiGirl? {
        if (sApiGirl == null) {
            synchronized(ApiManager::class.java) {
                if (sApiGirl == null) {
                    sApiGirl = Retrofit.Builder()
                            .baseUrl(GIRL_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(ApiGirl::class.java)
                }
            }
        }
        return sApiGirl
    }
}