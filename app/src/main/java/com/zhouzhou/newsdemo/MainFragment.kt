package com.zhouzhou.newsdemo

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.bean.Channel
import com.zhouzhou.basemodule.viewmodule.NewsViewModule
import com.zhouzhou.baseview.BaseFragment
import com.zhouzhou.newsdemo.databinding.FragmentMainBinding

class MainFragment() : BaseFragment<FragmentMainBinding, NewsViewModule>() {

    private val logger = Logger("MainFragment")
    private var channel = Channel("头条")
    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun getBindingVariable(): Int {
        return BR.test
    }

    override fun getViewModule(): NewsViewModule {
        return ViewModelProvider(this).get(NewsViewModule::class.java)
    }

    override fun onChanged(t: Any?) {
        logger.d("data onchanged:" + LogUtil.objToString(t))
    }

    override fun lifecycleObserver() {
        super.lifecycleObserver()
        // 注册监听器，监听数据改变
        viewModel?.helloWorld?.observe(this, this)
        viewModel?.channel = channel
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