package com.yuan.wanandroid.test.project.presenter

import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.project.bean.ProjectTab
import com.yuan.wanandroid.test.project.contract.ProjectContract


class ProjectPresenter : BasePresenter<ProjectContract.View>(), ProjectContract.Presenter {

    override fun getProjectTabs() {
        addSubscribe(create(ApiService::class.java).getProjectTabs(), object : BaseObserver<List<ProjectTab>>() {
            override fun onSuccess(data: List<ProjectTab>?) {
                if (this@ProjectPresenter.isViewAttached()) {
                    this@ProjectPresenter.getView()?.onProjectTabs(data)
                }
            }
        })
    }
}