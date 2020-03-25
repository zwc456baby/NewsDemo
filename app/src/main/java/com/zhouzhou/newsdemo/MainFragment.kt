package com.zhouzhou.newsdemo

import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.bean.Channel
import com.zhouzhou.basemodule.bean.NewsBean
import com.zhouzhou.basemodule.viewmodule.NewsViewModule
import com.zhouzhou.baseview.BaseFragment
import com.zhouzhou.newsdemo.adapter.NewsAdapter
import com.zhouzhou.newsdemo.databinding.FragmentMainBinding

class MainFragment() : BaseFragment<FragmentMainBinding, NewsViewModule>() {

    private val logger = Logger("MainFragment")
    private var channel = Channel("头条")

    private var newsAdapter: NewsAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getViewModule(): NewsViewModule {
        return ViewModelProvider(this).get(NewsViewModule::class.java)
    }

    override fun onChanged(t: Any?) {
        logger.v("news data onchanged")
        if (t is ObservableArrayList<*>) {
            @Suppress("UNCHECKED_CAST")
            val list = t as ObservableArrayList<NewsBean.ResultBean.ListBean>
            newsAdapter?.setNewData(list)
            logger.d("success set news adapter new data")
        }
    }

    override fun lifecycleObserver() {
        super.lifecycleObserver()
        // 注册监听器，监听数据改变
//        viewModel?.helloWorld?.observe(this, this)
        viewModel?.newsData?.observe(this, this)
        viewModel?.channel = channel
        val viewDataBinding = viewDataBinding
        if (viewDataBinding is FragmentMainBinding) {
            val context = getContext() ?: return

            if (newsAdapter == null) {
                newsAdapter = NewsAdapter(ArrayList(), context)
            }
            viewDataBinding.newsRecycler.layoutManager = LinearLayoutManager(context)
            viewDataBinding.newsRecycler.adapter = newsAdapter
            logger.d("success set news recycler view adapter")
            viewDataBinding.newsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                        /**
                         * 上拉触顶，则重新加载
                         */
                        if (recyclerView.computeVerticalScrollOffset() == 0) {
                            viewModel?.reLoad()
                        }
                        /**
                         * 下拉触底，加载更多
                         */
                        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange()) {
                            logger.d("recycler scroll to bottom,request more")
                            viewModel?.nextPage()
                        }
                    }
                }
            })
        }
    }

    /**
     * 设置 channel 名称，公用一个 Fragmen 承载不同的 channel 列表
     */
    private fun setChannel(channel: Channel) {
        this.channel = channel
        viewModel?.channel = channel
    }

    companion object {
        fun newInstence(channel: Channel): Fragment {
            val fragment = MainFragment()
            fragment.setChannel(channel)
            return fragment
        }
    }

}