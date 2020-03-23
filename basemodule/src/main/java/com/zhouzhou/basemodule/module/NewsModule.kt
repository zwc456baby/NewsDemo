package com.zhouzhou.basemodule.module

import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.networkmodule.ApiUtil
import com.zhouzhou.networkmodule.api.RequestApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody

open class NewsModule : BaseModule<String>(), Observer<ResponseBody> {

    private val logger = Logger("NewsModule")
    private var islaod = false

    public fun isTest() {
//        callbackData()
    }

    fun requestNext(page: Int) {
        islaod = true
        logger.i("news module request:$page")
        ApiUtil.getService(RequestApi::class.java)
            .getNews("头条", 10, page * 10)
            .compose(ApiUtil.getInstant.applySchedulers(this))
            .subscribe()
    }

    fun isload(): Boolean {
        return islaod
//        callback
//        callbackData()
    }

    override fun onComplete() {
        logger.d("news module on complete")
    }

    override fun onSubscribe(d: Disposable) {
        addDisposable(d)
    }

    override fun onNext(t: ResponseBody) {
        logger.d("news module on next")
        val result = t.string()
        logger.d("news module result string:$result")
    }

    override fun onError(e: Throwable) {
        logger.e("request faild ,error:${LogUtil.objToString(e)}")
    }

    override fun destory() {
        islaod = false
        super.destory()
        logger.i("news module clear")
    }

}