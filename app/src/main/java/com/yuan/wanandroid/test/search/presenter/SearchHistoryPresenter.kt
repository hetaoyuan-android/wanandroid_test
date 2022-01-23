package com.yuan.wanandroid.test.search.presenter

import android.util.Log
import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.search.bean.SearchHot
import com.yuan.wanandroid.test.search.contract.SearchHistoryContract
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

class SearchHistoryPresenter : BasePresenter<SearchHistoryContract.View>(), SearchHistoryContract.Presenter {

    /**
     * 搜索热门
     */
    override fun getSearchHot() {
        addSubscribe(create(ApiService::class.java).getSearchHot(), object : BaseObserver<ArrayList<SearchHot>>() {
            override fun onSuccess(data: ArrayList<SearchHot>?) {
                if (this@SearchHistoryPresenter.isViewAttached()) {
                    Log.e("debug", "getSearchHot() = " + data?.size)
                    if (data != null) {
                        this@SearchHistoryPresenter.getView()?.onSearchHot(data)
                    }
                }
            }
        })
    }

    override fun addSearchHistory(keyword: String) {

    }


    /**
     * 搜索历史
     */
    override fun getSearchHistory() {
        Observable.create(object : ObservableOnSubscribe<Any> {
            override fun subscribe(emitter: ObservableEmitter<Any>) {

//                emitter.onNext()
            }
        })


    }

}