package com.yuan.wanandroid.test.base.mvp

import com.yuan.wanandroid.test.base.BaseActivity

abstract class BaseMVPActivity<in V: IView, P: IPresenter<in V>> : BaseActivity(), IView {

    protected lateinit var presenter:P
    override fun initData() {
        super.initData()
        presenter =createPresenter()
        presenter.attachView(this as V)
    }

    abstract fun createPresenter(): P
}