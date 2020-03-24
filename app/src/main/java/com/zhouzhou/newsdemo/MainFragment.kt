package com.zhouzhou.newsdemo

import androidx.lifecycle.ViewModelProvider
import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.viewmodule.NewsViewModule
import com.zhouzhou.baseview.BaseFragment
import com.zhouzhou.newsdemo.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding, NewsViewModule>() {

    private val logger = Logger("MainFragment")
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
    }

}