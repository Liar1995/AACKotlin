package com.sunmeng.aackotlin.common.convert

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl
import io.reactivex.Observable

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class LiveDataObservableAdapter {
    companion object {
        fun fromObservable(observable: Observable<GirlData<List<Girl>>>, mIsLoadingGirlList: MutableLiveData<Boolean>?)
                : MutableLiveData<GirlData<List<Girl>>> {
            return ObservableToLiveData(observable,mIsLoadingGirlList)
        }
    }
}