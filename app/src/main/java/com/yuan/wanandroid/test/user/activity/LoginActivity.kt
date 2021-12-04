package com.yuan.wanandroid.test.user.activity

import android.text.SpannableString
import android.text.TextUtils
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.yuan.wanandroid.test.R
import com.yuan.wanandroid.test.base.mvp.BaseMVPActivity
import com.yuan.wanandroid.test.db.bean.User
import com.yuan.wanandroid.test.user.activity.contract.LoginContract
import com.yuan.wanandroid.test.user.presenter.LoginPresenter
import com.yuan.wanandroid.test.utils.LoginView
import com.yuan.wanandroid.test.widgets.ClearEditText

class LoginActivity : BaseMVPActivity<LoginContract.View, LoginPresenter>(), LoginContract.View,
    View.OnClickListener {

    private lateinit var usernameEditText: ClearEditText
    private lateinit var passwordEditText: ClearEditText
    private lateinit var loginView: LoginView
    private lateinit var registerTxtView: TextView
    private lateinit var closeImgView: ImageView

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun initView() {
        closeImgView = findViewById(R.id.iv_login_close)
        usernameEditText = findViewById(R.id.cet_login_username)
        passwordEditText = findViewById(R.id.cet_login_password)
        loginView = findViewById(R.id.lv_login)
        registerTxtView = findViewById(R.id.tv_user_register)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
        super.initData()
        val spannableString = SpannableString(registerTxtView.text.toString().trim())
        spannableString.setSpan(
            UnderlineSpan(),
            0,
            registerTxtView.text.toString().trim().length,
            SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
        )
        registerTxtView.text = spannableString
        registerTxtView.setOnClickListener(this)
        loginView.setOnClickListener(this)
        closeImgView.setOnClickListener(this)
    }

    override fun onLoginResult(userName: String, user: User?) {
        finishLoginActivity()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_login_close -> {
                finishLoginActivity()
            }
            R.id.lv_login -> {
                login()
            }
            R.id.tv_user_register -> {
//                gotoRegisterActivity()
            }
        }
    }

    private fun login() {
        val userName = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(mContext, "请输入用户名", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_LONG).show()
            return
        }
        presenter.login(userName, password)
        loginView.setState(LoginView.STATE_LOADING)
    }

    /**
     * 关闭 LoginActivity
     */
    private fun finishLoginActivity() {
        finish()
        overridePendingTransition(0, R.anim.anim_bottom_exit)
    }

    override fun onDestroy() {
        super.onDestroy()
        loginView.release()
    }

}