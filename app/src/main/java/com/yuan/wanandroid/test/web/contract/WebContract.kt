package com.yuan.wanandroid.test.web.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.web.bean.AddFavoriteResponse


interface WebContract {
    interface View : IView {
        fun onAddFavorited(addFavoriteResponse: AddFavoriteResponse?)
    }

    interface Presenter {
        fun addFavorite(id: Int, title: String, author: String, link: String)
    }
}