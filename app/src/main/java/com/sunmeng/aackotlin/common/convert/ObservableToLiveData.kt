package com.sunmeng.aackotlin.common.convert

import android.arch.lifecycle.LiveData
import android.support.annotation.NonNull
import com.sunmeng.aackotlin.data.remote.model.GirlData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class ObservableToLiveData<T> internal constructor(@param:NonNull private val mObservable: Observable<T>) : LiveData<GirlData<T>>() {

    private var mDisposableRef: WeakReference<Disposable>? = null
    private val mLock = Any()

    override fun onActive() {
        super.onActive()
        mObservable.subscribe {
            object : Observer<T> {
                override fun onSubscribe(d: Disposable) {
                    synchronized(mLock) { mDisposableRef = WeakReference(d) }
                }

                override fun onError(e: Throwable) {
                    synchronized(mLock) { mDisposableRef = null }
                }

                override fun onNext(t: T) {
                    if (t != null) postValue(GirlData.content(t))
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
            if (subscriptionRef!=null){
                val subscription = subscriptionRef.get()
                subscription?.dispose()
                mDisposableRef = null
            }
        }
    }
}