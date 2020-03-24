package com.zhouzhou.baseview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zhouzhou.basemodule.viewmodule.IViewModule

abstract class BaseFragment<DB : ViewDataBinding, VM : IViewModule> : Fragment(),
    Observer<Any> {

    protected var viewModel: VM? = null
    protected var viewDataBinding: DB? = null

    abstract fun getLayoutId(): Int
    open protected fun getBindingVariable(): Int {
        return 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lifecycleObserver()

        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        if (getBindingVariable() > 0) {
            // viewModel 不能为空，否则会绑定失败。界面没有数据
            viewDataBinding?.setVariable(getBindingVariable(), viewModel)
        }
        viewDataBinding?.executePendingBindings()

        return viewDataBinding?.root
    }

    abstract fun getViewModule(): VM

    /**
     * 子类重写此方法时，注意调用 父类，否则不会有 observer
     */
    open fun lifecycleObserver() {
        viewModel = getViewModule()
        viewModel?.let {
            lifecycle.addObserver(it)
        }
    }
}