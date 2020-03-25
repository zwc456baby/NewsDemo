package com.zhouzhou.newsdemo.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.zhou.logutils.LogUtil
import com.zhouzhou.basemodule.bean.NewsBean
import com.zhouzhou.basemodule.common.CommonAdapter
import com.zhouzhou.basemodule.viewmodule.NewItemVM
import com.zhouzhou.newsdemo.databinding.RecycleritemNewsBinding

class NewItemView(context: Context) : FrameLayout(context) {

    private val newItemVM = NewItemVM()
    private var dataBinding: RecycleritemNewsBinding? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        dataBinding = RecycleritemNewsBinding.inflate(inflater, this, false)
        if (dataBinding == null) {
            LogUtil.e("news adapter databinding is null")
        }
        addView(dataBinding!!.root)
        setOnClickListener {
            newItemVM.click()
        }
    }

    fun setData(bean: NewsBean.ResultBean.ListBean) {
        newItemVM.setData(bean)

        dataBinding?.newbean = bean
        dataBinding?.executePendingBindings()
        dataBinding?.newItemImg?.apply {
            CommonAdapter.loadImage(this, bean.pic)
        }
    }

}