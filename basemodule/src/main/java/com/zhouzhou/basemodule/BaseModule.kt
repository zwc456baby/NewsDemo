package com.zhouzhou.basemodule.module

import com.zhouzhou.basemodule.modulecallback.BaseCallback
import io.reactivex.Observer
import java.lang.ref.Reference
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseModule<D> {
    private var compositeDisposable: CompositeDisposable? = null

    protected var mReferenceQueue: ReferenceQueue<BaseCallback<D>> = ReferenceQueue()
    protected var mWeakListenerArrayList: ConcurrentLinkedQueue<WeakReference<BaseCallback<D>>> =
        ConcurrentLinkedQueue()

    @Synchronized
    fun register(registerCallback: BaseCallback<D>) {
        clearOldCallBack()
        for (wkCallback in mWeakListenerArrayList) {
            val callback: BaseCallback<D>? = wkCallback.get()
            if (callback == registerCallback) {
                return
            }
        }
        val weakListener = WeakReference<BaseCallback<D>>(registerCallback, mReferenceQueue)
        mWeakListenerArrayList.add(weakListener)
    }

    @Synchronized
    fun unregister(registerCallback: BaseCallback<D>) {
        clearOldCallBack()
        for (weakListener in mWeakListenerArrayList) {
            val listenerItem = weakListener.get()
            if (registerCallback === listenerItem) {
                mWeakListenerArrayList.remove(weakListener)
                break
            }
        }
    }

    private fun clearOldCallBack() {
        var releaseListener: Reference<out BaseCallback<D>>?
        do {
            releaseListener = mReferenceQueue.poll()
            if (releaseListener != null) {
                mWeakListenerArrayList.remove(releaseListener)
            }
        } while ((releaseListener) != null)
    }

    fun callbackData(success: Boolean, data: D, any: Any) {
        for (weakListener in mWeakListenerArrayList) {
            val listenerItem = weakListener.get()
            if (listenerItem != null) {
                if (success) {
                    listenerItem.success(this, data, any)
                } else {
                    listenerItem.faild(this, data)
                }
            }
        }
    }

    fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    open fun destory() {
        compositeDisposable?.dispose()
    }
}