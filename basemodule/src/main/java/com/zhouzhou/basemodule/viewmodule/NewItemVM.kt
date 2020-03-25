package com.zhouzhou.basemodule.viewmodule

import com.zhou.logutils.LogUtil
import com.zhouzhou.basemodule.bean.NewsBean
import com.zhouzhou.basemodule.module.BaseModule

class NewItemVM : BaseViewModule<NewsBean.ResultBean.ListBean
        , BaseModule<NewsBean.ResultBean.ListBean>>() {

    private val TAG = "NewItemVM"
    private var itemBean: NewsBean.ResultBean.ListBean? = null

    init {

    }

    fun setData(bean: NewsBean.ResultBean.ListBean) {
        itemBean = bean
    }

    fun click() {
        LogUtil.d(TAG, "click bena:${LogUtil.objToString(itemBean)}")
    }

}