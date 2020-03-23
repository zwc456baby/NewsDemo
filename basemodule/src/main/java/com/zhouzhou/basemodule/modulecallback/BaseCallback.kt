package com.zhouzhou.basemodule.modulecallback

import com.zhouzhou.basemodule.module.BaseModule

interface BaseCallback<D> {
    fun success(module: BaseModule<D>, data: D, any: Any?)
    fun faild(module: BaseModule<D>, data: D)
}