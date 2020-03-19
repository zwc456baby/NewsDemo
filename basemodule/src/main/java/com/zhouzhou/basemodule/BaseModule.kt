package com.zhouzhou.basemodule.module

abstract class BaseModule {

    abstract fun reload()

    abstract fun firstLoad()

    abstract fun requestNext()

    abstract fun isload(): Boolean

    abstract fun cancel()
}