package com.example.home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.commonlibrary.router.MyARouter
import com.example.commonlibrary.ui.CommonActivity
import com.example.commonlibrary.util.Constants
import com.example.home.R
import com.example.home.databinding.HomeFragmentArticleSecondBinding
import com.example.home.ui.HomeRepository
import com.example.home.ui.HomeVMFactory
import com.example.home.ui.HomeViewModel
import com.example.home.ui.adapter.ArticleLatestProjectAdapter
import kotlinx.android.synthetic.main.home_fragment_article_second.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 12:45
 *
 */


class HomeLatestProjectFragment : BaseMVVMFragment<HomeFragmentArticleSecondBinding, HomeViewModel>() {


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

        val articleLatestProjectAdapter = ArticleLatestProjectAdapter()
        home_article_recycler.setHasFixedSize(true)
        home_article_recycler.layoutManager = LinearLayoutManager(context)
        home_article_recycler.adapter = articleLatestProjectAdapter

        vm.projectArticleList.observe(viewLifecycleOwner, Observer {
            articleLatestProjectAdapter.replaceData(it)

        })
        articleLatestProjectAdapter.setOnItemClickListener(object : ArticleLatestProjectAdapter.OnItemClickListener{
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

            override fun onItemClick(view: View, link: String?, title: String?) {

                MyARouter.openActivity(
                    Constants.Page.ACTIVITY_COMMON,
                    CommonActivity.TYPE_WEB_VIEW,link!!,title!!)

            }

        })

    }

    fun initRequest(){
        vm.getLatestProject()
    }

}