package com.yuan.wanandroid.test.search.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.search.bean.SearchHistory
import com.yuan.wanandroid.test.search.bean.SearchHot

interface SearchHistoryContract {

    interface View: IView {
        fun onSearchHot(searchHots: ArrayList<SearchHot>)
        fun onSearchHistory(searchHistory: ArrayList<SearchHistory>?)
    }

    interface Presenter {

        /**
         * 获取搜索热门
         */
        fun getSearchHot()

        /**
         * 获取搜索历史
         */
        fun getSearchHistory()

        /**
         * 添加搜索历史
         */
        fun addSearchHistory(keyword: String)

    }
}