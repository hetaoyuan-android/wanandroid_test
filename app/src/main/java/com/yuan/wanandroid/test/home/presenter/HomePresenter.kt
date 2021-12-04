package com.yuan.wanandroid.test.home.presenter

import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.base.BaseResponse
import com.yuan.wanandroid.test.home.bean.Article
import com.yuan.wanandroid.test.home.bean.ArticleResponse
import com.yuan.wanandroid.test.home.bean.Banner
import com.yuan.wanandroid.test.home.contract.HomeContract
import com.yuan.wanandroid.test.http.BaseObserver
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private var dataList = ArrayList<Article>()
    override fun getBanner() {
        addSubscribe(create(ApiService::class.java).getBanner(), object : BaseObserver<List<Banner>>(){
            override fun onSuccess(action: List<Banner>?) {
                getView()?.onBanner(action)
            }

        })
    }

    override fun getArticles(page: Int) {
        val apiService = create(ApiService::class.java)
        val zipObservable = Observable.zip(apiService.getTopArticle(), apiService.getArticles(page),
            BiFunction<BaseResponse<List<Article>>, BaseResponse<ArticleResponse>, BaseResponse<List<Article>>> { resp1, resp2 ->
                if (page == 0) {
                    dataList.clear()
                    val topArticles = resp1.data
                    if (topArticles != null) {
                        dataList.addAll(topArticles)
                    }
                }
                val data = resp2.data
                if (data != null) {
                    val articles = data.datas
                    if (articles != null) {
                        dataList.addAll(articles)
                    }
                }
                // 因为 BaseObserver 范型指定了为 BaseResponse， 所以这里重新构造 BaseResponse 对象作为返回值
                BaseResponse(dataList, dataList, resp1.errorMsg, resp1.errorCode, false)
            })

        addSubscribe(zipObservable, object : BaseObserver<List<Article>>() {
            override fun onSuccess(data: List<Article>?) {
                getView()?.onArticles(page, data)
            }
        })
    }

}