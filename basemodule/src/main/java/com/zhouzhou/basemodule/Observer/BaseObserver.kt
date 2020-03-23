package com.zhouzhou.basemodule.Observer

import com.zhouzhou.basemodule.module.BaseModule
import com.zhouzhou.basemodule.modulecallback.BaseCallback
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class BaseObserver<T> : Observer<T> {
    //    private val baseModule: BaseModule<D> = module
    private var callBack: BaseCallback<T>? = null

    fun init(callback: BaseCallback<T>): BaseObserver<T> {
        this.callBack = callBack
        return this
    }

    override fun onComplete() {
        callBack = null
    }

    override fun onSubscribe(d: Disposable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNext(t: T) {
//        this.callBack?.callbackData(true,)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}