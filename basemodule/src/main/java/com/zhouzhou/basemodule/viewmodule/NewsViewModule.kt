package com.zhouzhou.basemodule.viewmodule

import androidx.databinding.ObservableArrayList
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
    var newsData: MutableLiveData<ObservableArrayList<NewsBean.ResultBean.ListBean>> = MutableLiveData()
    var channel: Channel = Channel("头条")
    var curPage = 0

    init {
        newsData.value = ObservableArrayList()
        module = NewsModule()
//        helloWorld.observe
    }

    override fun success(module: BaseModule<NewsBean>, data: NewsBean, any: Any?) {
        logger.d("news view module success receive data")
        if (curPage == 0) {
            newsData.value?.clear()
        }
        curPage++

        // 只有 执行 postValue 才能使 View 的Observe onChanged 收到回调
//       否则只有界面数据改变
        newsData.value?.addAll(data.result.list)
        newsData.postValue(newsData.value)
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
        if (curPage == 0) {
            module?.requestNext(curPage, channel)
        }
    }

    /**
     * 重新加载
     */
    fun reLoad() {
        curPage = 0
        module?.requestNext(curPage, channel)
    }

    /**
     * 下一页
     */
    fun nextPage() {
        module?.requestNext(curPage, channel)
    }

    override fun onCleared() {
        super.onCleared()
        logger.d("listenner on clear")
        module?.unregister(this)
        module?.destory()
        module = null
    }

}