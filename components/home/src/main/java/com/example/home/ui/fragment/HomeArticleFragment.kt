package com.example.home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.commonlibrary.util.GlideImageLoader
import com.example.home.databinding.HomeFragmentArticleBinding
import com.example.home.ui.HomeRepository
import com.example.home.ui.HomeVMFactory
import com.example.home.ui.HomeViewModel
import com.example.commonlibrary.adapter.CommonArticleAdapter
import com.example.commonlibrary.router.MyARouter
import com.example.commonlibrary.ui.CommonActivity
import com.example.commonlibrary.util.Constants
import com.example.home.R
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.home_fragment_article.*
import kotlinx.android.synthetic.main.home_fragment_recycler_item.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 9:28
 *
 */


class HomeArticleFragment : BaseMVVMFragment<HomeFragmentArticleBinding, HomeViewModel>(){

    val images = ArrayList<String>()
    val titles = ArrayList<String>()
    val urls = ArrayList<String>()

    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this,
            HomeVMFactory(HomeRepository())
        ).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.home_fragment_article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm


        initView()
        initRequest()

    }

    fun initView(){
        home_banner.setImageLoader(GlideImageLoader())
        home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        home_banner.setDelayTime(2000)
        home_banner.setBannerAnimation(Transformer.DepthPage)
        vm.bannerData.observe(viewLifecycleOwner, Observer {

            images.clear()
            titles.clear()
            urls.clear()

            it.forEach {
                images.add(it.imagePath!!)
                titles.add(it.title!!)
                urls.add(it.url!!)
            }
            home_banner.setImages(images)
            home_banner.setBannerTitles(titles)
            home_banner.start()


        })

        home_banner.setOnBannerListener { position ->

            MyARouter.openActivity(Constants.Page.ACTIVITY_COMMON,CommonActivity.TYPE_WEB_VIEW,urls[position],titles[position])

        }

        val homeAdapter = CommonArticleAdapter()
        //设置layoutManager
        home_article_recycler.layoutManager = LinearLayoutManager(context)
        home_article_recycler.adapter = homeAdapter


        vm.articleList.observe(viewLifecycleOwner, Observer {

            homeAdapter.replaceData(it)



        })


        homeAdapter.setOnItemClickListener(object: CommonArticleAdapter.OnItemClickListener {
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

                        MyARouter.openActivity(Constants.Page.ACTIVITY_COMMON,CommonActivity.TYPE_ARTICLE_SORT_FRAGMENT,name,id!!)

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
        vm.getHomeBannerData(context!!)
        vm.getHomeArticleList()

    }

}