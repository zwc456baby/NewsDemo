package com.zhouzhou.newsdemo

import com.zhouzhou.basemodule.viewmodule.NewsViewModule
import com.zhouzhou.baseview.BaseFragment
import com.zhouzhou.newsdemo.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding, NewsViewModule>() {
    override fun getLayoutId(): Int {
//        return R.id.fragment_main
        return 0
    }

    override fun getViewModule(): NewsViewModule {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChanged(t: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}