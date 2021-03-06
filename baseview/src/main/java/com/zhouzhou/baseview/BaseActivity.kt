package com.zhouzhou.baseview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.zhouzhou.basemodule.viewmodule.IViewModule

abstract class BaseActivity<DB : ViewDataBinding, VM : IViewModule> : AppCompatActivity(),
    Observer<Any> {

    var viewModel: VM? = null
    var viewDataBinding: DB? = null

    abstract fun getLayoutId(): Int
    open protected fun getBindingVariable(): Int {
        return 0
    }

    abstract fun getViewModule(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModule()
        viewModel?.let {
            lifecycle.addObserver(it)
        }
        bindLayout()
    }

    private fun bindLayout() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        if (getBindingVariable() > 0) {
            viewDataBinding?.setVariable(getBindingVariable(), viewModel)
        }
        viewDataBinding?.executePendingBindings()
    }

}