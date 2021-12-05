package com.yuan.wanandroid.test.project

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.yuan.wanandroid.test.R
import com.yuan.wanandroid.test.base.mvp.BaseMVPFragment
import com.yuan.wanandroid.test.common.bean.FragmentItem
import com.yuan.wanandroid.test.project.adapter.ProjectPageAdapter
import com.yuan.wanandroid.test.project.bean.ProjectTab
import com.yuan.wanandroid.test.project.contract.ProjectContract
import com.yuan.wanandroid.test.project.presenter.ProjectPresenter
import com.yuan.wanandroid.test.widgets.ProjectViewPager


private const val ARG_PARAM1 = "param1"

class ProjectFragment : BaseMVPFragment<ProjectContract.View,ProjectPresenter>(), ProjectContract.View {

    private var param1: String? = null
    private lateinit var adapter: ProjectPageAdapter

    companion object {
        @JvmStatic
        fun newInstance() = ProjectFragment()

    }

    override fun createPresenter(): ProjectPresenter {
        return ProjectPresenter()
    }

    override fun initData() {
        super.initData()
        presenter.getProjectTabs()
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_project
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        val tabLayout: TabLayout? = rootView?.findViewById(R.id.tl_project_tabs)
        val viewPager: ProjectViewPager? = rootView?.findViewById(R.id.vp_project_pager)
        val fragmentList: MutableList<FragmentItem> = mutableListOf<FragmentItem>()
        adapter = ProjectPageAdapter(childFragmentManager, fragmentList)
        viewPager?.adapter = adapter
        tabLayout?.setupWithViewPager(viewPager)
    }



    override fun onProjectTabs(projectTabs: List<ProjectTab>?) {
        val projectTabsList = getFragmentItems(projectTabs)
        adapter.setDataSource(projectTabsList)
    }

    private fun getFragmentItems(projectTabs: List<ProjectTab>?): List<FragmentItem> {
        val list: MutableList<FragmentItem> = mutableListOf()
        if (projectTabs != null) {
            for (projectTab in projectTabs) {
                list.add(
                    FragmentItem(projectTab.name, ProjectPageFragment.newInstance(projectTab.id))
                )
            }
        }
        return list
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }
}