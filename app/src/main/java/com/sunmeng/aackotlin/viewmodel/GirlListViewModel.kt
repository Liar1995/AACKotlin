package com.sunmeng.aackotlin.viewmodel

import android.app.Application
import android.arch.core.util.Function
import android.arch.lifecycle.*
import com.sunmeng.aackotlin.data.DataRepository
import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl
import com.sunmeng.aackotlin.utils.Util

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:因为ViewModel的生命周期是和Activity或Fragment分开的，
 * 所以在ViewModel中绝对不能引用任何View对象或者任何引用了Activity的Context的对象。
 * 如果ViewModel中需要Application的Context的话，可以继承AndroidViewModel
 */
class GirlListViewModel(application: Application, girlsDataRepository: DataRepository) : AndroidViewModel(application) {

    private val mGirlPageIndex = MutableLiveData<Int>()

    var mGirls: LiveData<GirlData<List<Girl>>>? = null

    private var mGirlsDataRepository: DataRepository? = girlsDataRepository

    init {
        mGirls = Transformations.switchMap(mGirlPageIndex, { input -> mGirlsDataRepository?.getGirlList(input) })
    }

    fun refreshGrilsData() {
        mGirlPageIndex.value = 1
    }

    fun loadNextPageGirls() {
        if (!Util.isNetworkConnected(getApplication())) return
        mGirlPageIndex.value = if (mGirlPageIndex.value == null) 1 else mGirlPageIndex.value!! + 1
    }

    fun getLoadMoreState(): MutableLiveData<Boolean>? {
        return mGirlsDataRepository?.isLoadingGirlList()
    }

    class Factory(private val mApplication: Application, private val mGirlsDataRepository: DataRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GirlListViewModel(mApplication,mGirlsDataRepository) as T
        }
    }
}