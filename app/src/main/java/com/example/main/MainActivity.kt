package com.example.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.commonlibrary.base.BaseActivity
import com.example.commonlibrary.router.MyARouter
import com.example.commonlibrary.util.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : BaseActivity() {


    override fun initContentViewID(): Int = R.layout.activity_main

    override fun fitTransparentStatus() {
        super.fitTransparentStatus()
        main_toolbar.fitTransparentStatus()
    }

    var mainFragmentManager : MainFragmentManager? = null

    override fun onViewCreated() {
        super.onViewCreated()
        //初始化Fragment管理类
        mainFragmentManager = MainFragmentManager(supportFragmentManager, home_container.id)

        //设置底部导航选择监听
        home_nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        home_nav_view.selectedItemId = R.id.nav_home

    }

    private fun setTitleText(title : String){
        main_toolbar.setBackground(R.color.colorPrimary)
        main_toolbar.setTitle(title)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->


        when (item.itemId) {
            R.id.nav_home -> {
                setTitleText(getString(R.string.page_home))
                mainFragmentManager!!.select(0)
                return@OnNavigationItemSelectedListener true
            }


            R.id.nav_knowledge -> {
                setTitleText(getString(R.string.page_system))
                mainFragmentManager!!.select(1)
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_official_account -> {
                setTitleText(getString(R.string.weichat_account))
                mainFragmentManager!!.select(2)
                return@OnNavigationItemSelectedListener true

            }

            R.id.nav_navigation -> {
                setTitleText(getString(R.string.navigation))
                mainFragmentManager!!.select(3)
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_mine -> {
                setTitleText(getString(R.string.mine))
                mainFragmentManager!!.select(4)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    class MainFragmentManager(val fragmentManager: FragmentManager, val containerId : Int){

        var fragments = mutableListOf<Fragment>()

        var lastFragment = 0



        init {
            val homeFragment       by lazy { MyARouter.getFragment(Constants.Page.FRAGMENT_HOME) }

            val knowledgeFragment  by lazy { MyARouter.getFragment(Constants.Page.FRAGMENT_KNOWLEDGE)}

            val weichatFragment    by lazy { MyARouter.getFragment(Constants.Page.FRAGMENT_WEICHAT)}

            val navigationFragment by lazy { MyARouter.getFragment(Constants.Page.FRAGMENT_NAVIGATION)}

            val mineFragment       by lazy { MyARouter.getFragment(Constants.Page.FRAGMENT_MINE)}

            fragments.add(homeFragment)
            fragments.add(knowledgeFragment)
            fragments.add(weichatFragment)
            fragments.add(navigationFragment)
            fragments.add(mineFragment)
            fragmentManager.beginTransaction().replace(containerId,fragments[0]).commit()
        }

        fun select(position: Int) {
            val transaction = fragmentManager.beginTransaction()
            if (lastFragment != position) {
                //隐藏上一个fragment
                transaction.hide(fragments[lastFragment])
                //如果这个fragment没有添加到Transaction中，那么进行添加
                if (!fragments[position].isAdded) {
                    transaction.add(containerId, fragments[position])
                }
                transaction.show(fragments[position]).commitAllowingStateLoss()
                lastFragment = position
            }
        }

    }
}
