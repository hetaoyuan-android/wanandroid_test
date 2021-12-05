package com.yuan.wanandroid.test.project

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.yuan.wanandroid.test.R
import com.yuan.wanandroid.test.base.BaseLazyFragment
import com.yuan.wanandroid.test.project.adapter.ProjectAdapter
import com.yuan.wanandroid.test.project.bean.Project
import com.yuan.wanandroid.test.project.bean.ProjectResponse
import com.yuan.wanandroid.test.project.contract.ProjectPageContract
import com.yuan.wanandroid.test.project.presenter.ProjectPagePresenter
import com.yuan.wanandroid.test.utils.*
import com.yuan.wanandroid.test.web.WebViewActivity
import com.yuan.wanandroid.test.widgets.LinearItemDecoration


private const val CID = "cid"
class ProjectPageFragment : BaseLazyFragment<ProjectPageContract.View, ProjectPagePresenter>(), ProjectPageContract.View {

    private var mCurPage: Int = 1
    private var cid: Int = 0
    private var listener: OnFragmentInteractionListener? = null
    private var recyclerView: RecyclerView? = null
    private var refreshLayout: SmartRefreshLayout? = null
    private lateinit var mAdapter: ProjectAdapter
    private var dataList = ArrayList<Project>()

    override fun getLayoutResId(): Int {
        return R.layout.fragment_project_page
    }

    override fun createPresenter(): ProjectPagePresenter {
        return ProjectPagePresenter()
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        recyclerView = rootView?.findViewById(R.id.rv_project)
        val itemDecoration = LinearItemDecoration(mContext).color(mContext.resources.getColor(R.color.white_eaeaea))
            .height(1f)
            .margin(15f, 15f)
        recyclerView?.addItemDecoration(itemDecoration)
        refreshLayout = rootView?.findViewById(R.id.srl_project)
        refreshLayout?.setEnableRefresh(false)
        recyclerView?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        setListener()
    }

    private fun setListener() {
        refreshLayout?.setOnLoadMoreListener { presenter.getProjectLists(mCurPage, cid) }
    }

    override fun loadData() {
        presenter.getProjectLists(mCurPage, cid)
        mAdapter = ProjectAdapter(R.layout.item_project)
        recyclerView?.adapter = mAdapter
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener{
                adapter, view, position -> onItemClick(position)
        }
        mAdapter.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position -> onItemChildClick(position) }
    }

    override fun onProjectLists(page: Int, response: ProjectResponse?) {
        refreshLayout?.finishLoadMore()
        mCurPage += 1;
        if (response != null) {
            dataList.addAll(response.datas)
        }
        mAdapter.setNewData(response?.datas)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cid = it.getInt(CID)
        }
    }


    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    fun onItemClick(position: Int) {
        // TODO
        val bundle = Bundle()
        val bean = dataList.get(position)
        bundle.putString(URL, bean.link)
        bundle.putInt(ID, bean.id)
        bundle.putString(AUTHOR, bean.author)
        bundle.putString(LINK, bean.link)
        bundle.putString(TITLE, bean.title)
        gotoActivity(
            mContext as Activity,
            WebViewActivity().javaClass,
            bundle
        )
    }

    fun onItemChildClick(position: Int) {
        //TODO
//        val imgUrl = dataList.get(position).envelopePic
//        val list = ArrayList<String>()
//        list.add(imgUrl)
//        val bundle = Bundle()
//        bundle.putStringArrayList(IMAGES, list)
//        gotoActivity(
//            mContext as Activity,
//            ImageBrowseActivity().javaClass,
//            bundle
//        )
    }

    companion object {
        @JvmStatic
        fun newInstance(cid: Int) =
            ProjectPageFragment().apply {
                arguments = Bundle().apply {
                    putInt(CID, cid)
                }
            }
    }

}