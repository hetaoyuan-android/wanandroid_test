package com.yuan.wanandroid.test.system

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yuan.wanandroid.test.R
import com.yuan.wanandroid.test.base.mvp.BaseMVPFragment
import com.yuan.wanandroid.test.system.adapter.SystemCategoryAdapter
import com.yuan.wanandroid.test.system.adapter.SystemContentAdapter
import com.yuan.wanandroid.test.system.bean.SystemCategory
import com.yuan.wanandroid.test.system.contract.SystemContract
import com.yuan.wanandroid.test.system.presenter.SystemPresenter
import com.yuan.wanandroid.test.utils.CID
import com.yuan.wanandroid.test.utils.TITLE
import com.yuan.wanandroid.test.utils.gotoActivity

class SystemFragment : BaseMVPFragment<SystemContract.View, SystemPresenter>(), SystemContract.View {

    private var categoryRecyclerView: RecyclerView? = null
    private var contentRecyclerView: RecyclerView? = null
    private lateinit var categoryAdapter: SystemCategoryAdapter
    private lateinit var contentAdapter: SystemContentAdapter
    private var dataList: List<SystemCategory> = ArrayList()
    companion object {
        @JvmStatic
        fun newInstance() = SystemFragment()
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_system
    }

    override fun createPresenter(): SystemPresenter {
        return SystemPresenter()
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        categoryRecyclerView = rootView?.findViewById(R.id.rv_system_category)
        contentRecyclerView = rootView?.findViewById(R.id.rv_system_content)
    }

    override fun initData() {
        super.initData()
        categoryRecyclerView?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        categoryAdapter = SystemCategoryAdapter(R.layout.item_system_category)
        categoryAdapter.onItemClickListener = object: BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                categoryAdapter.setClickedPosition(position)
                val systemCategory = dataList[position]
                contentAdapter.setNewData(systemCategory.children)
            }

        }

        contentRecyclerView?.layoutManager = GridLayoutManager(mContext, 2)
        contentAdapter = SystemContentAdapter(R.layout.item_system_content)
        contentRecyclerView?.adapter = contentAdapter
        contentAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val cid = contentAdapter.data[position].id
                val title = contentAdapter.data[position].name
                val bundle = Bundle()
                bundle.putInt(CID, cid)
                bundle.putString(TITLE, title)
                gotoActivity(mContext as Activity, SystemArticleActivity().javaClass, bundle)
            }
        }

        categoryRecyclerView?.adapter = categoryAdapter
        // 请求数据
        presenter.getSystemCategory()
    }



    override fun onSystemCategory(data: List<SystemCategory>?) {
        if (data != null) {
            dataList = data
        }
        categoryAdapter.setNewData(dataList)
        contentAdapter.setNewData(dataList[0].children)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }
}