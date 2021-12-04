package com.yuan.wanandroid.test.home.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.home.bean.Article
import com.yuan.wanandroid.test.home.bean.Banner

interface HomeContract {
    interface View : IView {
        fun onBanner(list: List<Banner>?)

        fun onArticles(page: Int, list: List<Article>?)
    }

    interface Presenter {
        fun getBanner()
        fun getArticles(page: Int)
    }
}