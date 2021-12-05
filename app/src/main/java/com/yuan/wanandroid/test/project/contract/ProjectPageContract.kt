package com.yuan.wanandroid.test.project.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.project.bean.ProjectResponse


interface ProjectPageContract {
    interface View : IView {
        fun onProjectLists(page: Int, response: ProjectResponse?)
    }

    interface Presenter {
        /**
         * 项目列表
         */
        fun getProjectLists(page: Int, cid: Int)

    }

}