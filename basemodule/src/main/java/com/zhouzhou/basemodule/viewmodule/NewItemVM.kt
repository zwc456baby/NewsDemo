package com.zhouzhou.basemodule.viewmodule

import com.zhou.logutils.LogUtil
import com.zhouzhou.basemodule.bean.NewsBean
import com.zhouzhou.basemodule.callback.NewItemClickCallback
import com.zhouzhou.basemodule.module.BaseModule

class NewItemVM : BaseViewModule<NewsBean.ResultBean.ListBean
        , BaseModule<NewsBean.ResultBean.ListBean>>() {

    private val TAG = "NewItemVM"
    private var itemBean: NewsBean.ResultBean.ListBean? = null
    private var callback: NewItemClickCallback? = null

    init {

    }

    fun setCallback(callback: NewItemClickCallback?) {
        this.callback = callback
    }

    fun setData(bean: NewsBean.ResultBean.ListBean) {
        itemBean = bean
    }

    fun click() {
        LogUtil.d(TAG, "user click new item ,VM call back to Fragment")
        callback?.apply {
            itemBean?.let {
                this.onClick(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        itemBean = null
        callback = null
    }

}