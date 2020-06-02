package com.example.userarticles.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibrary.adapter.CommonArticleAdapter
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.commonlibrary.util.Constants
import com.example.userarticles.R
import com.example.userarticles.databinding.FragmentAuthorArticleBinding
import kotlinx.android.synthetic.main.fragment_author_article.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 13:59
 *
 */


class ArticleSortFragment : BaseMVVMFragment<FragmentAuthorArticleBinding, AuthorArticleViewModel>() {

    companion object{


        fun newInstance(cid : Int) : ArticleSortFragment {

            val fragment = ArticleSortFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.SP.CID,cid)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun initViewModel(): AuthorArticleViewModel =
        ViewModelProvider(this, AuthorArticleVMFactory(AuthorArticleRepository())).get(
            AuthorArticleViewModel::class.java)

    override fun initContentViewID(): Int =
        R.layout.fragment_author_article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initRequest()

        //设置layoutManager
        author_article_recycler.layoutManager = LinearLayoutManager(context)

        val homeArticleAdapter = CommonArticleAdapter()
        author_article_recycler.adapter = homeArticleAdapter



        vm.articleSortList.observe(viewLifecycleOwner, Observer {

            homeArticleAdapter.replaceData(it)

        })


        homeArticleAdapter.setOnItemClickListener(object : CommonArticleAdapter.OnItemClickListener {

            override fun onItemClick(id: Int, collect: Boolean) {

                when (collect) {

                    true -> {

                        vm.unCollect(id)
                    }

                    false -> {
                        vm.collect(id)
                    }
                }

            }

            //todo
            override fun onItemClick(view: View, name: String?, id: Int?, link: String?, title: String?) {
//                view.context.startActivity<TitleWithContentActivity>(
//                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
//                    Pair(Constants.SP.URL,link),
//                    Pair(Constants.SP.WEBVIEW_TITLE,title)
//                )

            }
        })

    }



    fun initRequest(){


        vm.getArticleSort(arguments!!.getInt(Constants.SP.CID,-1))

    }
}