package com.zhouzhou.basemodule.viewmodule

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.bean.Channel
import com.zhouzhou.basemodule.bean.ChannelBean
import com.zhouzhou.basemodule.module.BaseModule
import com.zhouzhou.basemodule.module.ChannelModule
import com.zhouzhou.basemodule.modulecallback.BaseCallback

class TabViewModule : BaseViewModule<ChannelBean, ChannelModule>(), BaseCallback<ChannelBean> {
    private val logger = Logger("ChannelViewModule")

    var channelList: MutableLiveData<ObservableArrayList<Channel>> = MutableLiveData()

    init {
        channelList.value = ObservableArrayList()
        module = ChannelModule()

        /**
         * 使用 Eventbus 接收跳转到 WebView 的消息
         * 如果不使用 EventBus ，则需要在多个 Fragment 以及 ViewModule 之间传递 引用，耦合严重
         * 不知是否有更好的实现方式
         * （当然也可以通过一个 Activity ，单独承载 WebView 页面，使用 Context 进行跳转。但这样的话，
         * 就不是之前所想的单Activity + 多Fragment 的App结构
         * ）
         * 所以问题就是：如何在多个 Fragment 之间传递数据
         *
         * 目前换回使用 Callback 的方式
         */
//        EventBus.getDefault().register(this)
    }

    override fun success(module: BaseModule<ChannelBean>, data: ChannelBean, any: Any?) {
        logger.d("success receive channel module")
        channelList.value?.clear()
        //将 channel String 转成 对象形式，后面如果有扩展字段。可以增加
        for (channelStr in data.result) {
            channelList.value?.add(Channel(channelStr))
        }
        // 调用 postValue ，使 observe 的 onChanged 可以被调用，使界面更新
        channelList.postValue(channelList.value)
    }

    override fun faild(module: BaseModule<ChannelBean>, data: ChannelBean) {
        logger.d("faild receive channel module")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        module?.register(this)
        // 每次启动时获取一次列表即可
        module?.getChannelList()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        logger.d("channel view module onResume")
    }

    override fun onCleared() {
        super.onCleared()
//        EventBus.getDefault().unregister(this)
        module?.unregister(this)
        module?.destory()
        module = null
    }

}