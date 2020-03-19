package com.zhouzhou.basemodule.module

import com.zhou.logutils.Logger

open class NewsModule : BaseModule() {
    private val logger = Logger("NewsModule")
    private var islaod = false

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