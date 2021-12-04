package com.yuan.wanandroid.test.setting.presenter

import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.setting.bean.LogoutResult
import com.yuan.wanandroid.test.setting.contract.SettingContract

class SettingPresenter : BasePresenter<SettingContract.View>(), SettingContract.Presenter {
    override fun logout() {
        addSubscribe(
            create(ApiService::class.java).logout(),
            object : BaseObserver<LogoutResult>() {
                override fun onSuccess(action: LogoutResult?) {
                    getView()?.onLogoutResult()
                }
            })
    }
}