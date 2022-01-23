package com.yuan.wanandroid.test.search.presenter

import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.search.bean.SearchResultResponse
import com.yuan.wanandroid.test.search.contract.SearchResultContract


class SearchResultPresenter : BasePresenter<SearchResultContract.View>(), SearchResultContract.Presenter {
    override fun getSearchResult(page: Int, keyword: String?) {
        if (keyword == null) {
            return
        }
        addSubscribe(
            create(ApiService::class.java).getSearchResult(page, keyword),
            object : BaseObserver<SearchResultResponse>() {
                override fun onSuccess(response: SearchResultResponse?) {
                    if (this@SearchResultPresenter.isViewAttached()) {
                        this@SearchResultPresenter.getView()?.onSearchResult(page, response)
                    }
                }
            })
    }


}