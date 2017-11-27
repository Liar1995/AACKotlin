package com.sunmeng.aackotlin.data.remote.api

import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
interface ApiGirl {

    @GET("api/data/福利/10/{index}")
    fun getGirlsData(@Path("index") index: Int): Observable<List<Girl>>

}