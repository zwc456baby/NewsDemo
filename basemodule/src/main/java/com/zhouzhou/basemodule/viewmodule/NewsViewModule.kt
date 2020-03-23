package com.zhouzhou.basemodule.viewmodule

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.module.BaseModule
import com.zhouzhou.basemodule.module.NewsModule
import com.zhouzhou.basemodule.modulecallback.BaseCallback

class NewsViewModule : BaseViewModule<String, NewsModule>(), BaseCallback<String> {

    private val logger = Logger("NewsViewModule")
    var helloWorld: MutableLiveData<ObservableField<String>> = MutableLiveData()
    var isFirst = true
    var curPage = 0

    init {
        helloWorld.value = ObservableField("hello world")
        module = NewsModule()
    }

    override fun success(module: BaseModule<String>, data: String, any: Any) {
        logger.d("news view module success receive data:${LogUtil.objToString(data)}")
    }

    override fun faild(module: BaseModule<String>, data: String) {
        logger.d("news view module faild receive data:${LogUtil.objToString(data)}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun createListenner() {
        module?.register(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destoryListenner() {
        module?.unregister(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        logger.d("listenner on resume ")
        if (isFirst) {
            isFirst = false
            module?.requestNext(0)
        } else {
            module?.requestNext(curPage)
        }
        curPage++
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destory() {
        module?.destory()
    }

    override fun onCleared() {
        super.onCleared()
        module?.unregister(this)
        module?.destory()
        module = null
//        helloWorld = null
    }

}