package com.yuan.wanandroid.test.project.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.project.bean.ProjectTab


interface ProjectContract {
    interface View : IView {
        fun onProjectTabs(projectTabs: List<ProjectTab>?)
    }

    interface Presenter {
        fun getProjectTabs()
    }

}