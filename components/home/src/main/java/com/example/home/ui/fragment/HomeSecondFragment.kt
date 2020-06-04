package com.example.home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.home.R
import com.example.home.databinding.HomeFragmentArticleSecondBinding
import com.example.home.ui.HomeRepository
import com.example.home.ui.HomeVMFactory
import com.example.home.ui.HomeViewModel
import com.example.commonlibrary.adapter.CommonArticleAdapter
import com.example.commonlibrary.router.MyARouter
import com.example.commonlibrary.ui.CommonActivity
import com.example.commonlibrary.util.Constants
import kotlinx.android.synthetic.main.home_fragment_article_second.*
import kotlinx.android.synthetic.main.home_fragment_recycler_item.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/21 16:53
 *
 */


class HomeSecondFragment : BaseMVVMFragment<HomeFragmentArticleSecondBinding, HomeViewModel>(){

    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this,
            HomeVMFactory(HomeRepository())
        ).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.home_fragment_article_second

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm


        initView()
        initRequest()

    }


    fun initView(){


        val homeArticleAdapter = CommonArticleAdapter()
        //设置layoutManager
        home_article_recycler.layoutManager = LinearLayoutManager(context)
        home_article_recycler.adapter = homeArticleAdapter


        vm.articleList.observe(viewLifecycleOwner, Observer {

            homeArticleAdapter.replaceData(it)



        })


        homeArticleAdapter.setOnItemClickListener(object: CommonArticleAdapter.OnItemClickListener {
            override fun onItemClick(id: Int, collect : Boolean) {

                when(collect){

                    true ->{

                        vm.unCollect(id)
                    }

                    false ->{
                        vm.collect(id)
                    }
                }

            }


            override fun onItemClick(view: View, name: String?, id: Int?, link: String?, title: String?) {

                when(view.id){

                    article_chapter.id ->{

                        MyARouter.openActivity(Constants.Page.ACTIVITY_COMMON, CommonActivity.TYPE_ARTICLE_SORT_FRAGMENT,name,id!!)

                    }
                    article_author.id ->{

                        MyARouter.openActivity(Constants.Page.ACTIVITY_COMMON,CommonActivity.TYPE_AUTHOR_ARTICLE_FRAGMENT,name,id!!)

                    }

                    article_layout.id ->{
                        MyARouter.openActivity(Constants.Page.ACTIVITY_COMMON,CommonActivity.TYPE_WEB_VIEW,link!!,title!!)

                    }

                }

            }

        })
    }

    fun initRequest(){

        vm.getSquareList()

    }

}