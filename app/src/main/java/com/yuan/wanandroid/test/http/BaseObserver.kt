package com.yuan.wanandroid.test.http

import android.util.Log
import com.yuan.wanandroid.test.base.BaseResponse
import com.yuan.wanandroid.test.base.mvp.IView
import io.reactivex.observers.DisposableObserver

abstract class BaseObserver<T> : DisposableObserver<BaseResponse<T>> {
    private var baseView: IView? = null

    constructor() : super()

    constructor(view: IView?) : super() {
        baseView = view
    }

    override fun onStart() {
        super.onStart()
        baseView?.showLoading()
    }

    override fun onNext(response: BaseResponse<T>) {
        baseView?.dismissLoading()
        Log.e("debug", "response = ${response.errorCode}")
        val errorCode: Int = response.errorCode ?: -1
        val errorMsg: String = response.errorMsg ?: ""
        val error: Boolean = response.error ?: true
        if ((errorCode == 0) or (errorCode == 200)) {
            onSuccess(response.data)
        } else {
            onError(ApiException(errorCode, errorMsg))
        }
    }

    override fun onError(e: Throwable) {
        ExceptionHandler.handleException(e)
    }

    override fun onComplete() {
        baseView?.dismissLoading()
    }

    abstract fun onSuccess(action: T?)


}