package com.yuan.wanandroid.test

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.BitmapFactory
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.jaeger.library.StatusBarUtil
import com.yuan.wanandroid.test.base.mvp.BaseMVPActivity
import com.yuan.wanandroid.test.common.EventBusSubscribe
import com.yuan.wanandroid.test.common.bean.FragmentItem
import com.yuan.wanandroid.test.db.DbManager
import com.yuan.wanandroid.test.db.bean.User
import com.yuan.wanandroid.test.gank.GankFragment
import com.yuan.wanandroid.test.home.HomeFragment
import com.yuan.wanandroid.test.main.adapter.MainViewPageAdapter
import com.yuan.wanandroid.test.main.bean.LoggedInEvent
import com.yuan.wanandroid.test.main.contract.MainContract
import com.yuan.wanandroid.test.main.presenter.MainPresenter
import com.yuan.wanandroid.test.project.ProjectFragment
import com.yuan.wanandroid.test.search.SearchActivity
import com.yuan.wanandroid.test.setting.SettingActivity
import com.yuan.wanandroid.test.system.SystemFragment
import com.yuan.wanandroid.test.user.activity.LoginActivity
import com.yuan.wanandroid.test.utils.blur
import com.yuan.wanandroid.test.utils.gotoActivity
import com.yuan.wanandroid.test.utils.isCookieNotEmpty
import com.yuan.wanandroid.test.widgets.MainViewPager
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@EventBusSubscribe
class MainActivity : BaseMVPActivity<MainContract.View, MainPresenter>(), MainContract.View,
    View.OnClickListener {

    private lateinit var mainMenu: ImageView
    private lateinit var mainSearch: ImageView
    private lateinit var mainTabLayout: TabLayout
    private lateinit var mainViewPager: MainViewPager
    private lateinit var mAdapter: MainViewPageAdapter
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var avatarBackground: ImageView
    private lateinit var usernameTextView: TextView
    private var loggedIn = false

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        drawerLayout = findViewById(R.id.dl_drawer_layout)
        StatusBarUtil.setColorForDrawerLayout(
            this,
            drawerLayout,
            resources.getColor(R.color.colorPrimary),
            0
        )
        navigationView = findViewById(R.id.nv_left_navigation)
        val headerView: View = navigationView.getHeaderView(0)
        usernameTextView = headerView.findViewById(R.id.tv_nav_username)
        avatarBackground = headerView.findViewById(R.id.iv_avatar_background)
        mainMenu = findViewById(R.id.iv_main_menu)
        mainSearch = findViewById(R.id.iv_main_search)
        mainTabLayout = findViewById(R.id.tl_main_tab)
        mainViewPager = findViewById(R.id.vp_main_pager)

        mainMenu.setOnClickListener {
            openDrawer()
        }
        usernameTextView.setOnClickListener(this)
        navigationView.setNavigationItemSelectedListener { item ->
            closeDrawer()
            when (item.itemId) {
                R.id.item_nav_happy_minute -> {
//                    gotoActivity(mContext as Activity, MeiziActivity().javaClass)
                }
                R.id.item_nav_setting -> {
                    gotoActivity(mContext as Activity, SettingActivity().javaClass)
                }
            }

            true
        }
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.avatar)
        avatarBackground.setImageBitmap(blur(mContext, bitmap, 22))
    }

    override fun initData() {
        super.initData()
        val list = mutableListOf<FragmentItem>()
        list.add(FragmentItem("首页", HomeFragment.newInstance()))
        list.add(FragmentItem("项目", ProjectFragment.newInstance()))
        list.add(FragmentItem("体系", SystemFragment.newInstance()))
        list.add(FragmentItem("干货", GankFragment.newInstance()))
        mAdapter = MainViewPageAdapter(this, supportFragmentManager, list)
        mainViewPager.adapter = mAdapter
        mainTabLayout.setupWithViewPager(mainViewPager)

        for (i in 0..mainTabLayout.tabCount) {
            val tabView: TabLayout.Tab? = mainTabLayout.getTabAt(i)
            tabView?.customView =mAdapter.getTabView(i)
        }
        //默认选中第一个
        mainViewPager.currentItem = 0
        changeTabView(mainTabLayout.getTabAt(0), 22f, true)
        mainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                changeTabView(tab, 18f, false)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                changeTabView(tab, 22f, true)
            }

        })
        setUsernameFromCache()
        presenter.getUserInfo()
        mainSearch.setOnClickListener(this)
    }

    private fun setUsernameFromCache() {
        loggedIn = isCookieNotEmpty(mContext)
        if (!loggedIn) {
            usernameTextView.text = getString(R.string.click_to_login)
        } else {
            val user = getCacheUser()
            val userName : String
            userName = if (user != null) {
                user.username
            } else {
                ""
            }
            usernameTextView.text = userName
        }
    }

    private fun getCacheUser(): User? {
        val users = DbManager.getInstance().getUserDao().loadAll()
        if (users != null && users.size > 0) {
            return users[0]
        }
        return null
    }

    private fun changeTabView(tab: TabLayout.Tab?, textSize: Float, isSelected: Boolean) {
        val view: View? = tab?.customView
        val textView: TextView? = view?.findViewById(R.id.tv_tab_title)
        textView?.textSize = textSize
        if (isSelected) {
            textView?.setTextColor(resources.getColor(android.R.color.black))
            val width = textView?.measuredWidth
            Log.e("debug", "width = $width")
        } else {
            textView?.setTextColor(resources.getColor(R.color.gray_959698))
        }
    }


    override fun onUserInfo(user: User) {
        Log.e("MainActivity", user.toString())
        loggedIn = isCookieNotEmpty(mContext)
        if (loggedIn) {
            usernameTextView.text = user.username
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoginStatusChanged(event: LoggedInEvent) {
        val user = event.user
        Log.e("MainActivity", "onLoginStatusChanged" + event.user.toString())
        setUsername(user)
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_nav_username -> {
                loggedIn = isCookieNotEmpty(mContext)
                if (!loggedIn) {
                    gotoLoginActivity()
                    closeDrawer()
                }
            }

            //搜索
            R.id.iv_main_search -> {
                gotoSearchActivity()
                overridePendingTransition(0, 0)
            }
        }
    }

    private fun gotoSearchActivity() {
        gotoActivity(this, SearchActivity().javaClass)
    }

    private fun gotoLoginActivity() {
        gotoActivity(this, LoginActivity().javaClass)
    }

    /**
     * 打开抽屉
     */
    @SuppressLint("WrongConstant")
    private fun openDrawer() {
        drawerLayout.openDrawer(Gravity.START)
    }

    /**
     * 关闭抽屉
     */
    @SuppressLint("WrongConstant")
    private fun closeDrawer() {
        drawerLayout.closeDrawer(Gravity.START)
    }

    private fun setUsername(user: User?) {
        if(user != null) {
            usernameTextView.text = user.username
        } else{
            usernameTextView.text = getString(R.string.click_to_login)
        }
    }
}