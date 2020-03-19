package com.zhouzhou.baseview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import com.zhouzhou.baseviewmodule.IViewModule

abstract class BaseActivity<DB : ViewDataBinding, VM : IViewModule> : AppCompatActivity(),
    Observer<Any> {

    private var viewModel: VM? = null
    private var viewDataBinding: DB? = null

    abstract fun getLayoutId(): Int
    open protected fun getBindingVariable(): Int {
        return 0
    }

    abstract fun getViewModule(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModule()
        bindLayout()
        viewModel?.let {
            lifecycle.addObserver(it)
        }
//        viewModel.
//        viewModel?.viewState
//        viewModel?.viewState.

    }

    private fun bindLayout() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        if (getBindingVariable() > 0) {
            viewDataBinding?.setVariable(getBindingVariable(), viewModel);
        }
        viewDataBinding?.executePendingBindings();
    }


}