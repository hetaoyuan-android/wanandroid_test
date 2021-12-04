package com.yuan.wanandroid.test.user.presenter

import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.db.DbManager
import com.yuan.wanandroid.test.db.bean.User
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.main.bean.LoggedInEvent
import com.yuan.wanandroid.test.user.activity.contract.LoginContract
import org.greenrobot.eventbus.EventBus

class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {
    override fun login(userName: String, password: String) {
        addSubscribe(create(ApiService::class.java).login(userName, password), object : BaseObserver<User>(getView()) {
            override fun onSuccess(user: User?) {
                getView()?.onLoginResult(userName, user)
                DbManager.getInstance().getUserDao().deleteAll()
                DbManager.getInstance().getUserDao().insert(user)
                EventBus.getDefault().post(LoggedInEvent(user))
            }

        })
    }

}