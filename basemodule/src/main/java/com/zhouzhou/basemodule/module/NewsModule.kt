package com.zhouzhou.basemodule.module

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.bean.Channel
import com.zhouzhou.basemodule.bean.NewsBean
import com.zhouzhou.networkmodule.ApiUtil
import com.zhouzhou.networkmodule.api.RequestApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody

open class NewsModule : BaseModule<NewsBean>(), Observer<ResponseBody> {

    private val logger = Logger("NewsModule")
    private var islaod = false

    @SuppressLint("CheckResult")
    fun requestNext(page: Int, channel: Channel) {
        islaod = true
        logger.i("news module request:$page")
        ApiUtil.getService(RequestApi::class.java)
            .getNews(channel.channel, 10, page * 10)
//                使用 compose 之后，不能再调用 subscribe() 否则无法获取到 error处理
            .compose(ApiUtil.getInstant.applySchedulers(this))
    }

    fun isload(): Boolean {
        return islaod
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
//        logger.d("news module result string:$result")

        val gson = Gson()
        val bean = gson.fromJson(result, NewsBean::class.java)
        callbackData(true, bean, null)
    }

    override fun onError(e: Throwable) {
        logger.e("request faild ,error:${LogUtil.objToString(e)}")
        callbackData(false, NewsBean(), null)
    }

    override fun destory() {
        islaod = false
        super.destory()
        logger.i("news module clear")
    }
}