package com.zhouzhou.newsdemo.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.zhouzhou.basemodule.bean.NewsBean
import com.zhouzhou.newsdemo.view.NewItemView


class ItemViewHolder(var newItemView: NewItemView) :
    RecyclerView.ViewHolder(newItemView) {

    fun bindData(data: NewsBean.ResultBean.ListBean) {
        newItemView.setData(data)
    }
}