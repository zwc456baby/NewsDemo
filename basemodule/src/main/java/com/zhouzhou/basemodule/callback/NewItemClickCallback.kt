package com.zhouzhou.basemodule.callback

import com.zhouzhou.basemodule.bean.NewsBean

interface NewItemClickCallback {
    fun onClick(newBean: NewsBean.ResultBean.ListBean)
}