package com.yuan.wanandroid.test.setting

import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.scwang.smartrefresh.layout.util.SmartUtil.dp2px
import com.yuan.wanandroid.test.R
import com.yuan.wanandroid.test.app.MainApp
import com.yuan.wanandroid.test.base.mvp.BaseMVPActivity
import com.yuan.wanandroid.test.main.bean.LoggedInEvent
import com.yuan.wanandroid.test.setting.contract.SettingContract
import com.yuan.wanandroid.test.setting.presenter.SettingPresenter
import com.yuan.wanandroid.test.utils.isCookieNotEmpty
import org.greenrobot.eventbus.EventBus

class SettingActivity : BaseMVPActivity<SettingContract.View, SettingPresenter>(),
    SettingContract.View {

    private lateinit var logoutBtn: Button
    private lateinit var toolbar: Toolbar

    override fun createPresenter(): SettingPresenter {
        return SettingPresenter()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_setting
    }

    override fun initView() {
        toolbar = findViewById(R.id.tb_setting)
        logoutBtn = findViewById(R.id.btn_logout)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "设置"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = dp2px(5f).toFloat()
        toolbar.setNavigationOnClickListener { finish() }
        logoutBtn.setOnClickListener {
            View.OnClickListener { presenter.logout() }
        }
    }



    override fun initData() {
        super.initData()
        val loggedIn = isCookieNotEmpty(mContext)
        if (loggedIn) {
            logoutBtn.visibility = View.VISIBLE
        } else {
            logoutBtn.visibility = View.GONE
        }
    }

    override fun onLogoutResult() {
        MainApp.getInstance().getPersistentCookieJar().clear()
        EventBus.getDefault().post(LoggedInEvent(null))
        finish()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

}