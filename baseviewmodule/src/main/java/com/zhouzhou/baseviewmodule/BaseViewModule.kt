package com.zhouzhou.baseviewmodule

import androidx.lifecycle.MutableLiveData
import com.zhouzhou.basemodule.module.BaseModule

abstract class BaseViewModule<M : BaseModule> : IViewModule() {
    protected var module: M? = null
    public val viewState: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
    }

}