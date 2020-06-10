package com.example.home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibrary.CommonApplication.Companion.commonContext
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.commonlibrary.util.Constants
import com.example.home.R
import com.example.home.databinding.HomeFragmentBinding
import com.example.home.ui.HomeRepository
import com.example.home.ui.HomeVMFactory
import com.example.home.ui.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */

@Route(path = Constants.Page.FRAGMENT_HOME)
class HomeFragment : BaseMVVMFragment<HomeFragmentBinding, HomeViewModel>() {



    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this, HomeVMFactory(
            HomeRepository()
        )
        ).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.home_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initView()

    }

    fun initView(){



        home_viewpager.adapter = HomePageAdapter(childFragmentManager)
        home_tab.setupWithViewPager(home_viewpager)


    }


    class HomePageAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){


        private val fragments = mutableListOf<Fragment>()
        private val titles = mutableListOf<String>()

        init {

            titles.add(commonContext.getString(R.string.page_home))
            titles.add(commonContext.getString(R.string.page_square))
            titles.add(commonContext.getString(R.string.page_project))

            fragments.add(HomeArticleFragment())
            fragments.add(HomeSecondFragment())
            fragments.add(HomeLatestProjectFragment())
        }

        override fun getItem(position: Int): Fragment  = fragments[position]

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence? = titles[position]


    }

}