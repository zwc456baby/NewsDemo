package com.zhouzhou.basemodule.viewmodule

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.bean.Channel
import com.zhouzhou.basemodule.bean.NewsBean
import com.zhouzhou.basemodule.module.BaseModule
import com.zhouzhou.basemodule.module.NewsModule
import com.zhouzhou.basemodule.modulecallback.BaseCallback

class NewsViewModule : BaseViewModule<NewsBean, NewsModule>(), BaseCallback<NewsBean> {

    private val logger = Logger("NewsViewModule")
    var helloWorld: MutableLiveData<ObservableField<String>> = MutableLiveData()
    var channel: Channel = Channel("头条")
    var isFirst = true
    var curPage = 0

    init {
        helloWorld.value = ObservableField("hello world")
        module = NewsModule()
//        helloWorld.observe
    }

    override fun success(module: BaseModule<NewsBean>, data: NewsBean, any: Any?) {
        logger.d("news view module success receive data")

        helloWorld.value?.set("you success load data")
        // 只有 执行 postValue 才能使 View 的Observe onChanged 收到回调
//       否则只有界面数据改变
        helloWorld.postValue(helloWorld.value)
    }

    override fun faild(module: BaseModule<NewsBean>, data: NewsBean) {
        logger.d("news view module faild receive data:${LogUtil.objToString(data)}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun createListenner() {
        logger.d("listenner on create")
        module?.register(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        logger.d("listenner on resume")
        if (isFirst) {
            isFirst = false
            module?.requestNext(0, channel)
        } else {
            module?.requestNext(curPage, channel)
        }
        curPage++
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destory() {
        logger.d("listenner on destory")
        module?.unregister(this)
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