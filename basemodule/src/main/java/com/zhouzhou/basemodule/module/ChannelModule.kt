package com.zhouzhou.basemodule.module

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.bean.ChannelBean
import com.zhouzhou.networkmodule.ApiUtil
import com.zhouzhou.networkmodule.api.RequestApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody

class ChannelModule : BaseModule<ChannelBean>(), Observer<ResponseBody> {
    private val logger = Logger("ChannelModule")
    override fun onComplete() {
        logger.d("channel module com complete")
    }

    override fun onSubscribe(d: Disposable) {
        logger.d("ChannelModule on subscribe")
        addDisposable(d)
    }

    override fun onNext(t: ResponseBody) {
        logger.d("ChannelModule on next")
        // 将返回的 responseBody 解析成数据
        val result = t.string()
        if (!result.isNullOrEmpty()) {
            val gson = Gson()
            val resultBean = gson.fromJson(result, ChannelBean::class.java)
            callbackData(true, resultBean, null)
        } else {
            callbackData(false, ChannelBean(), null)
        }
    }

    override fun onError(e: Throwable) {
        logger.d("ChannelModule onError：" + LogUtil.objToString(e))
        callbackData(false, ChannelBean(), null)
    }

    @SuppressLint("CheckResult")
    fun getChannelList() {
        logger.d("get channel list")
        ApiUtil.getService(RequestApi::class.java)
            .getChannels()
//                使用 compose 之后，不能再调用 subscribe() 否则无法获取到 error处理
            .compose(ApiUtil.getInstant.applySchedulers(this))
    }

    override fun destory() {
        super.destory()
        logger.d("ChannelModule destory")
    }

}