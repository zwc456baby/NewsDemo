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

class ChannelViewModule : BaseViewModule<ChannelBean, ChannelModule>(), BaseCallback<ChannelBean> {
    private val logger = Logger("ChannelViewModule")

    var channelList: MutableLiveData<ObservableArrayList<Channel>> = MutableLiveData()

    init {
        channelList.value = ObservableArrayList()
        module = ChannelModule()
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
        module?.unregister(this)
        module?.destory()
        module = null
    }

}