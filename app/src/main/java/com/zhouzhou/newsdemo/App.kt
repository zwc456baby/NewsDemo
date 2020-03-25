package com.zhouzhou.newsdemo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.zhou.logutils.LogUtil

class App : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        var mContext: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        mContext = this
        LogUtil.getLogConfig().configEnableTrack(true)
    }
}