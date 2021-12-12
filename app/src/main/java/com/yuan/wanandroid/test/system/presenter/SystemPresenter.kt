package com.yuan.wanandroid.test.system.presenter

import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.system.bean.SystemCategory
import com.yuan.wanandroid.test.system.contract.SystemContract


class SystemPresenter : BasePresenter<SystemContract.View>(), SystemContract.Presenter {

    override fun getSystemCategory() {
        addSubscribe(create(ApiService::class.java).getSystem(), object : BaseObserver<List<SystemCategory>>() {
            override fun onSuccess(data: List<SystemCategory>?) {
                getView()?.onSystemCategory(data)
            }
        })
    }
}