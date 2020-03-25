package com.zhouzhou.newsdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zhou.logutils.LogUtil
import com.zhouzhou.basemodule.bean.NewsBean
import com.zhouzhou.basemodule.callback.NewItemClickCallback
import com.zhouzhou.newsdemo.databinding.RecycleritemNewsBinding
import com.zhouzhou.newsdemo.view.NewItemView
import com.zhouzhou.newsdemo.viewholder.ItemViewHolder


class NewsAdapter : RecyclerView.Adapter<ItemViewHolder> {
    private val TAG = "NewsAdapter"
    private var list: ArrayList<NewsBean.ResultBean.ListBean> = ArrayList()
    private var context: Context? = null
    private var callback: NewItemClickCallback? = null

    @Suppress("ConvertSecondaryConstructorToPrimary")
    constructor(
        list: ArrayList<NewsBean.ResultBean.ListBean>,
        context: Context,
        callback: NewItemClickCallback
    ) : super() {
        LogUtil.d("receive new list bean:${list.size}")
        this.list = list
        this.context = context
        this.callback = callback
    }

    fun setNewData(list: ArrayList<NewsBean.ResultBean.ListBean>) {
        LogUtil.d("receive new list bean:${list.size}")
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(NewItemView(parent.context, this.callback))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(list.get(position))
    }
}