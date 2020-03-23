package com.zhouzhou.newsdemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.viewmodule.NewsViewModule
import com.zhouzhou.baseview.BaseActivity
import com.zhouzhou.newsdemo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, NewsViewModule>() {
    override fun getViewModule(): NewsViewModule {
        // 注意，在 ViewModel 中，不能直接 new
        // 只有使用 ViewModelProviders 才能将生命周期绑定到 ViewModel 中，实现生命周期感知
//        // https://www.jianshu.com/p/e8955f525f4c
        return ViewModelProvider(this).get(NewsViewModule::class.java)
    }

    private val logger = Logger("MainActivity")
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getBindingVariable(): Int {
        return BR.test
    }

    /**
     * 数据改变回调,必须调用 observe 注册
     */
    override fun onChanged(t: Any?) {
        logger.i("listener on changed:" + LogUtil.objToString(t))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val handler = Handler(Looper.getMainLooper())
    }
}
