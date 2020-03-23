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

    private var viewModel: VM? = null
    private var viewDataBinding: DB? = null

    abstract fun getLayoutId(): Int
    open protected fun getBindingVariable(): Int {
        return 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        lifecycleObserver()
        return viewDataBinding?.root
    }

    abstract fun getViewModule(): VM

    fun lifecycleObserver() {
        viewModel = getViewModule()
        viewModel?.let {
            lifecycle.addObserver(it)
        }
    }
}