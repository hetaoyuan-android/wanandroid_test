package com.yuan.wanandroid.test.search.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.search.bean.SearchResultResponse


interface SearchResultContract {
    interface View : IView {
        fun onSearchResult(page: Int, response: SearchResultResponse?)
    }

    interface Presenter {
        fun getSearchResult(page: Int, keyword: String?)
    }
}