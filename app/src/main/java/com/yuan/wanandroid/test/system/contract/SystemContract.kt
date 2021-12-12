package com.yuan.wanandroid.test.system.contract

import com.yuan.wanandroid.test.base.mvp.IView
import com.yuan.wanandroid.test.system.bean.SystemCategory

interface SystemContract {

    interface View: IView {
        fun onSystemCategory(data: List<SystemCategory>?)
    }

    interface Presenter {
        /**
         * 获取系统分类
         */
        fun getSystemCategory()
    }
}