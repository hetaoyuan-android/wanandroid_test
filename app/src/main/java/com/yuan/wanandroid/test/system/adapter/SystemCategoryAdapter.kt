package com.yuan.wanandroid.test.system.adapter

import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuan.wanandroid.test.R
import com.yuan.wanandroid.test.system.bean.SystemCategory

class SystemCategoryAdapter(layoutId:Int): BaseQuickAdapter<SystemCategory, BaseViewHolder>(layoutId) {
    private var mClickedPosition: Int = 0

    override fun convert(helper: BaseViewHolder?, item: SystemCategory?) {
        val position = helper?.layoutPosition
        if (position == mClickedPosition) {
            helper.setTextColor(R.id.tv_system_category, ContextCompat.getColor(mContext, R.color.colorAccent))
            helper.setVisible(R.id.view_indicator, true)
            // 加粗体
            helper.setTypeface(R.id.tv_system_category, Typeface.defaultFromStyle(Typeface.BOLD))
        } else {
            helper?.setTextColor(R.id.tv_system_category, ContextCompat.getColor(mContext, R.color.black_f222))
            helper?.setVisible(R.id.view_indicator, false)
            // 取消粗体
            helper?.setTypeface(R.id.tv_system_category, Typeface.defaultFromStyle(Typeface.NORMAL))
        }
        helper?.setText(R.id.tv_system_category, item?.name)
    }

    fun setClickedPosition(position: Int) {
        this.mClickedPosition = position
        notifyDataSetChanged()
    }
}