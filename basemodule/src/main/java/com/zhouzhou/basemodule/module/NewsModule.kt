package com.zhouzhou.basemodule.module

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.modulecallback.BaseCallback

open class NewsModule<D> : BaseModule<D>() {

    private val logger = Logger("NewsModule")
    private var islaod = false

    public fun isTest() {
//        callbackData()
    }

    override fun requestNext() {
        islaod = true
    }

    override fun firstLoad() {
        islaod = true
        logger.i("news module first request")
    }

    override fun isload(): Boolean {
        return islaod
    }

    override fun cancel() {
        islaod = false
    }

    override fun reload() {
        islaod = true
    }

}