package com.yuan.wanandroid.test.web.presenter

import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.base.BaseResponse
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.web.bean.AddFavoriteResponse
import com.yuan.wanandroid.test.web.contract.WebContract
import io.reactivex.Observable

class WebPresenter : BasePresenter<WebContract.View>(), WebContract.Presenter {

    override fun addFavorite(id: Int, title: String, author: String, link: String) {
        val observable: Observable<BaseResponse<AddFavoriteResponse>>
        if (id == -1) {
            observable = create(ApiService::class.java).addFavorite(title, author, link)
        } else {
            observable = create(ApiService::class.java).addFavorite(id)
        }
        addSubscribe(observable, object : BaseObserver<AddFavoriteResponse>() {
            override fun onSuccess(data: AddFavoriteResponse?) {
                getView()?.onAddFavorited(data)
            }
        })
    }

}