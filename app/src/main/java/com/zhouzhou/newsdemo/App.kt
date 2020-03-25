package com.zhouzhou.newsdemo

import android.app.Application
import com.zhou.logutils.LogUtil

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtil.getLogConfig().configEnableTrack(true)
    }
}