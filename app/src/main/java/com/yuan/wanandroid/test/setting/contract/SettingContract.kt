package com.yuan.wanandroid.test.setting.contract

import com.yuan.wanandroid.test.base.mvp.IView

interface SettingContract {

    interface View : IView {
        fun onLogoutResult()
    }

    interface Presenter {
        fun logout()
    }
}