package com.yuan.wanandroid.test.system.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.home.bean.Article


interface SystemArticleContract {

    interface View : IView {
        fun onSystemArticles(page: Int, list: List<Article>?)
    }

    interface Presenter {
        fun getSystemArticles(page: Int, cid: Int)
    }

}