package com.sunmeng.aackotlin.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
interface DataSource {
    fun getGirlList(index: Int): LiveData<GirlData<List<Girl>>>
    fun isLoadingGirlList(): MutableLiveData<Boolean>?
}