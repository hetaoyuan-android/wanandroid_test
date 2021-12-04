package com.yuan.wanandroid.test.user.activity.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.db.bean.User

interface LoginContract {

    interface View : IView {
        fun onLoginResult(userName: String, user: User?)
    }

    interface Presenter {
        fun login(userName: String, password: String)
    }
}