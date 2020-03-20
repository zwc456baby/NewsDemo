package com.zhouzhou.basemodule.module

import com.zhou.logutils.Logger

open class NewsModule<D> : BaseModule<D>() {

    private val logger = Logger("NewsModule")
    private var islaod = false

    public fun isTest() {
//        callbackData()
    }

    fun requestNext(page: Int) {
        islaod = true
        logger.i("news module request:$page")
    }

    fun isload(): Boolean {
        return islaod
    }

    fun cancel() {
        islaod = false
    }

    override fun clear() {
        logger.i("news module clear")
    }

}