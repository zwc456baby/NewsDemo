package com.zhouzhou.basemodule.viewmodule

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.zhouzhou.basemodule.module.BaseModule
import com.zhouzhou.basemodule.module.NewsModule
import com.zhouzhou.basemodule.modulecallback.BaseCallback

class NewsViewModule : BaseViewModule<String, NewsModule<String>>(), BaseCallback<String> {

    var helloWorld: MutableLiveData<ObservableField<String>> = MutableLiveData()

    init {
        helloWorld.value = ObservableField("hello world")
    }

    override fun success(module: BaseModule<String>, data: String, any: Any) {

    }

    override fun faild(module: BaseModule<String>, data: String) {
    }

    init {
        module?.register(this)
    }

    fun test() {
//        module.re
//        viewState=""
    }

    override fun onCleared() {
        super.onCleared()
        module?.unregister(this)
    }

}