package com.example.weichat.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.commonlibrary.util.Constants
import com.example.weichat.R
import com.example.weichat.databinding.FragmentOfficialAccountBinding
import com.example.weichat.entity.OfficialAccountBean
import kotlinx.android.synthetic.main.fragment_official_account.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */

@Route(path = Constants.Page.FRAGMENT_WEICHAT)
class WeichatFragment : BaseMVVMFragment<FragmentOfficialAccountBinding, WeichatViewModel>() {
    override fun initViewModel(): WeichatViewModel =
        ViewModelProvider(this, WeichatVMFactory(WeichatRepository())).get(
            WeichatViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_official_account

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initData()
        initRequest()

    }

    fun initRequest(){
        vm.getOfficialAccountList()
    }

    fun initData(){
        vm.articleList.observe(viewLifecycleOwner, Observer {
            initWeChatArticleFragment(it)

        })
    }

    fun initWeChatArticleFragment(dataList : List<OfficialAccountBean.dataBean>){

        val tabs = arrayListOf<String>()
        val fragments = arrayListOf<Fragment>()


        for (data in dataList){
            tabs.add(data.name!!)
            fragments.add(WeichatArticleFragment.newInstance(data.id!!))
        }

        official_account_viewpager.adapter =
            officialAccountTabAdapter(childFragmentManager,tabs,fragments)
        official_account_tab.setViewPager(official_account_viewpager)


    }


    class officialAccountTabAdapter(
        fragmentManager: FragmentManager,
        val tabs: List<String>,
        val fragments: List<Fragment>
    ) : FragmentStatePagerAdapter(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = tabs.size

        override fun getPageTitle(position: Int): CharSequence? = tabs[position]
    }
}