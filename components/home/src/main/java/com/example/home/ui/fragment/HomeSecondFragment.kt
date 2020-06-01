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
import com.example.home.ui.adapter.HomeArticleAdapter
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


        val homeArticleAdapter = HomeArticleAdapter(R.layout.home_fragment_recycler_item)
        //设置layoutManager
        home_article_recycler.layoutManager = LinearLayoutManager(context)
        home_article_recycler.adapter = homeArticleAdapter


        vm.articleList.observe(viewLifecycleOwner, Observer {

            homeArticleAdapter.replaceData(it)



        })


        homeArticleAdapter.setOnItemClickListener(object: HomeArticleAdapter.OnItemClickListener {
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

                    //todo
                    article_chapter.id ->{
//                        view.context.startActivity<TitleWithContentActivity>(
//                            Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_ARTICLE_SORT_LIST),
//                            Pair(Constants.SP.ARTICLE_TITLE,name),
//                            Pair(Constants.SP.CID,id)
//                        )
                    }
                    article_author.id ->{

//                        view.context.startActivity<TitleWithContentActivity>(
//                            Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_USER_ARTICLE_LIST),
//                            Pair(Constants.SP.AUTHOR_NAME,name),
//                            Pair(Constants.SP.AUTHOR_ID,id)
//                        )
                    }

                    article_layout.id ->{
//                        view.context.startActivity<TitleWithContentActivity>(
//                            Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
//                            Pair(Constants.SP.URL,link),
//                            Pair(Constants.SP.WEBVIEW_TITLE,title)
//                        )
                    }

                }

            }

        })
    }

    fun initRequest(){

        vm.getSquareList()

    }

}