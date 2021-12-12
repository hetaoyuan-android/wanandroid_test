package com.yuan.wanandroid.test.system.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuan.wanandroid.test.R
import com.yuan.wanandroid.test.system.bean.SystemCategory

class SystemContentAdapter(layoutResId: Int) : BaseQuickAdapter<SystemCategory, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: SystemCategory?) {
        helper?.setText(R.id.tv_system_category, item?.name)
    }
}


