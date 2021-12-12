package com.yuan.wanandroid.test.system.presenter

import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.home.bean.ArticleResponse
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.system.contract.SystemArticleContract


class SystemArticlePresenter : BasePresenter<SystemArticleContract.View>(), SystemArticleContract.Presenter {

    override fun getSystemArticles(page: Int, cid: Int) {
        addSubscribe(create(ApiService::class.java).getSystemArticles(page, cid),
            object : BaseObserver<ArticleResponse>() {
                override fun onSuccess(response: ArticleResponse?) {
                    getView()?.onSystemArticles(page, response?.datas)
                }
            })
    }
}