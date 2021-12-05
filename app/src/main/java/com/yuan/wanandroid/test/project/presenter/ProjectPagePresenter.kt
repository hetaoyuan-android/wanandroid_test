package com.yuan.wanandroid.test.project.presenter

import com.yuan.wanandroid.test.project.contract.ProjectPageContract
import com.yuan.wanandroid.test.apiservice.ApiService
import com.yuan.wanandroid.test.base.BasePresenter
import com.yuan.wanandroid.test.http.BaseObserver
import com.yuan.wanandroid.test.project.bean.ProjectResponse

class ProjectPagePresenter : BasePresenter<ProjectPageContract.View>(), ProjectPageContract.Presenter {
    override fun getProjectLists(page: Int, cid: Int) {
        addSubscribe(
            create(ApiService::class.java).getProjectLists(page, cid),
            object : BaseObserver<ProjectResponse>() {
                override fun onSuccess(data: ProjectResponse?) {
                    if (this@ProjectPagePresenter.isViewAttached()) {
                        this@ProjectPagePresenter.getView()?.onProjectLists(page, data)
                    }
                }
            })
    }
}