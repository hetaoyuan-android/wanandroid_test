package com.yuan.wanandroid.test.base

import com.yuan.wanandroid.test.base.mvp.IPresenter
import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.http.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

open class BasePresenter<V : IView> : IPresenter<V> {

    private lateinit var viewReference: WeakReference<V>
    private var disposable: CompositeDisposable = CompositeDisposable()

    fun <T> addSubscribe(observable: Observable<BaseResponse<T>>, baseObserver: BaseObserver<T>) {
        val observer = observable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(baseObserver)
        disposable.add(observer)
    }

    fun <D> create(clazz: Class<D>): D {
        return RetrofitClient.get().retrofit.create(clazz)
    }


    override fun attachView(view: V) {
        viewReference = WeakReference(view)
    }

    override fun detachView() {
        viewReference.clear()
    }

    override fun isViewAttached(): Boolean {
        return viewReference.get() != null
    }

    override fun getView(): V? {
        return viewReference.get()
    }

    fun unsubscribe() {
        disposable.dispose()
    }

}