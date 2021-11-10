package com.yuan.wanandroid.test.main.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.db.bean.User

interface MainContract {
    interface View : IView {
        fun onUserInfo(user: User)
    }

    interface Presenter {
        fun getUserInfo()
    }

}