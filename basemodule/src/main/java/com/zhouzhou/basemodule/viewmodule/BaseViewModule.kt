package com.zhouzhou.basemodule.viewmodule

import androidx.lifecycle.MutableLiveData
import com.zhouzhou.basemodule.module.BaseModule

abstract class BaseViewModule<D, M : BaseModule<D>> : IViewModule() {
    protected var module: M? = null
    var viewState: MutableLiveData<String> = MutableLiveData()
}