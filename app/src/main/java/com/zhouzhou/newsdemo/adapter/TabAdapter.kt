package com.zhouzhou.newsdemo.adapter

import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zhou.logutils.Logger
import com.zhouzhou.basemodule.bean.Channel
import com.zhouzhou.newsdemo.MainFragment


class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val logger = Logger("TabAdapter")
    private val channels: ObservableArrayList<Channel> = ObservableArrayList()
    private val fragmentHashMap = HashMap<String, Fragment>()

    fun setChannels(inChannel: ObservableArrayList<Channel>) {
        logger.d("tab adapter channels changed")

        channels.clear()
        channels.addAll(inChannel)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        val key = channels.get(position).channel
        var fragmentTmp = fragmentHashMap.get(key)
        if (fragmentTmp != null) {
            return fragmentTmp
        }
        fragmentTmp = MainFragment.newInstence(channels.get(position))
        fragmentHashMap.put(key, fragmentTmp)
        return fragmentTmp
    }

    override fun getCount(): Int {
        return channels.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return channels.get(position).channel
    }

}