package com.yuan.wanandroid.test

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.yuan.wanandroid.test.base.mvp.BaseMVPActivity
import com.yuan.wanandroid.test.common.EventBusSubscribe
import com.yuan.wanandroid.test.db.bean.User
import com.yuan.wanandroid.test.main.contract.MainContract
import com.yuan.wanandroid.test.main.presenter.MainPresenter

@EventBusSubscribe
class MainActivity : BaseMVPActivity<MainContract.View, MainPresenter>(), MainContract.View, View.OnClickListener {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun getLayoutResId(): Int {
        TODO("Not yet implemented")
    }

    override fun createPresenter(): MainPresenter {
        TODO("Not yet implemented")
    }

    override fun onUserInfo(user: User) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}