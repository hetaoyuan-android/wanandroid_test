package com.yuan.wanandroid.test.main.presenter

import android.util.Log
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.db.DbManager
import com.yuan.wanandroid.test.main.contract.MainContract

class MainPresenter: BasePresenter<MainContract.View>(), MainContract.Presenter {
    override fun getUserInfo() {
        val users = DbManager.getInstance().getUserDao().loadAll()
        Log.e("MainPresenter", "users = $users")
        if (users.size > 0) {
            getView()?.onUserInfo(users[0])
        }
    }
}