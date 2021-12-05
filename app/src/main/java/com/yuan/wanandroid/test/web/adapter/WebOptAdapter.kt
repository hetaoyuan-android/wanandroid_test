package com.yuan.wanandroid.test.web.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuan.wanandroid.test.web.bean.WebOptBean
import com.yuan.wanandroid.test.R

class WebOptAdapter(layoutResId: Int, dataList: ArrayList<WebOptBean>?) : BaseQuickAdapter<WebOptBean, BaseViewHolder>(layoutResId, dataList) {

    override fun convert(helper: BaseViewHolder?, item: WebOptBean?) {
        val id = item?.resId ?: -1
        if (id != -1) {
            helper?.setImageResource(R.id.iv_web_opt_img, id)
        }

        helper?.setText(R.id.tv_web_opt_title, item?.title)
    }
}