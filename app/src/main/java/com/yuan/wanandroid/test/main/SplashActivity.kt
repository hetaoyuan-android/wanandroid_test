package com.yuan.wanandroid.test.main

import android.animation.Animator
import com.airbnb.lottie.LottieAnimationView
import com.yuan.wanandroid.test.MainActivity
import com.yuan.wanandroid.test.R
import com.yuan.wanandroid.test.base.BaseActivity
import com.yuan.wanandroid.test.utils.gotoActivity

class SplashActivity : BaseActivity() {

private lateinit var logoLottieView: LottieAnimationView

    override fun initView() {
        logoLottieView = findViewById(R.id.lav_logo)
        logoLottieView.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                gotoMainActivity()
            }

            override fun onAnimationCancel(animation: Animator?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })

    }

    private fun gotoMainActivity() {
        gotoActivity(this, MainActivity().javaClass)
        finish()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

}