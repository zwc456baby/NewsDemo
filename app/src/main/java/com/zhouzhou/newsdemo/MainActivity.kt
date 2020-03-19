package com.zhouzhou.newsdemo

import android.os.Bundle
import com.zhou.logutils.Logger
import com.zhouzhou.baseview.BaseActivity
import com.zhouzhou.baseviewmodule.NewsViewModule
import com.zhouzhou.newsdemo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, NewsViewModule>() {
    override fun getViewModule(): NewsViewModule {
        return NewsViewModule()
    }

    private val logger = Logger("MainActivity")
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getBindingVariable(): Int {
        return BR.test
    }

    override fun onChanged(t: Any?) {
//        viewDataBinding.test
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.d("mainactivity onCreate")
//        com.zhouzhou.newsdemo.BR.test
//        CameraAgentFactory
//        Databin
//        DataBindingUtil.
    }

}
