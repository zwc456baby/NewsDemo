package com.zhouzhou.newsdemo

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.zhou.logutils.LogUtil
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.bean.Channel
import com.zhouzhou.basemodule.viewmodule.ChannelViewModule
import com.zhouzhou.baseview.BaseFragment
import com.zhouzhou.newsdemo.adapter.TabAdapter
import com.zhouzhou.newsdemo.databinding.FragmentMainBinding
import com.zhouzhou.newsdemo.databinding.FragmentTabBinding

class TabFragment : BaseFragment<FragmentMainBinding, ChannelViewModule>() {
    private val logger = Logger("TabFragment")
    private var tabAdapter: TabAdapter? = null
    private var channels: ObservableArrayList<Channel> = ObservableArrayList()

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tab
    }

    override fun getViewModule(): ChannelViewModule {
        return ViewModelProvider(this).get(ChannelViewModule::class.java)
    }

    override fun lifecycleObserver() {
        super.lifecycleObserver()
        // 注册 观察者，数据改变会收到 onChanged 回调
        viewModel?.channelList?.observe(this, this)
        if (viewDataBinding is FragmentTabBinding) {
            logger.d(" set tab layout adapter")
            @Suppress("CAST_NEVER_SUCCEEDS")
            val viewDB = (viewDataBinding as FragmentTabBinding)
            tabAdapter = TabAdapter(childFragmentManager)
            tabAdapter?.setChannels(channels)

            viewDB.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            viewDB.viewpager.setAdapter(tabAdapter)
            viewDB.tablayout.setupWithViewPager(viewDB.viewpager)
            viewDB.viewpager.setOffscreenPageLimit(1);
        } else {
            logger.w(" viewDataBinding not FragmentTabBinding:" + LogUtil.objToString(viewDataBinding))
        }
    }

    override fun onChanged(t: Any?) {
//        logger.v("receive data onchanged:" + LogUtil.objToString(t))
        if (t == null) return
        if (t is ObservableArrayList<*>) {
            @Suppress("UNCHECKED_CAST")
            this.channels = (t as ObservableArrayList<Channel>)
            tabAdapter?.setChannels(t)
        }
    }

}