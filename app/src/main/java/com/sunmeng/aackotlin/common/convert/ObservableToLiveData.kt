package com.sunmeng.aackotlin.common.convert

import android.arch.lifecycle.MutableLiveData
import android.support.annotation.NonNull
import android.util.Log
import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class ObservableToLiveData internal
constructor(@param:NonNull private val mObservable: Observable<GirlData<List<Girl>>>,
            private var mIsLoadingGirlList: MutableLiveData<Boolean>?) : MutableLiveData<GirlData<List<Girl>>>() {

    private var mDisposableRef: WeakReference<Disposable>? = null
    private val mLock = Any()

    override fun onActive() {
        mIsLoadingGirlList?.value = false
        super.onActive()
        mObservable.subscribe {
            object : Observer<GirlData<List<Girl>>> {
                override fun onError(e: Throwable) {
                    synchronized(mLock) { mDisposableRef = null }
                }

                override fun onNext(t: GirlData<List<Girl>>) {
                    Log.i("summer", "post val")
                    postValue(t)
                }

                override fun onSubscribe(d: Disposable) {
                    synchronized(mLock) { mDisposableRef = WeakReference(d) }
                }

                override fun onComplete() {
                    synchronized(mLock) { mDisposableRef = null }
                }
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        synchronized(mLock) {
            val subscriptionRef = mDisposableRef
            if (subscriptionRef != null) {
                val subscription = subscriptionRef.get()
                subscription?.dispose()
                mDisposableRef = null
            }
        }
    }
}