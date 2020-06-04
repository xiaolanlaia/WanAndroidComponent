package com.example.userarticles.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibrary.adapter.CommonArticleAdapter
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.commonlibrary.util.Constants
import com.example.userarticles.R
import com.example.userarticles.databinding.FragmentAuthorArticleBinding
import kotlinx.android.synthetic.main.fragment_author_article.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 8:59
 *
 */

@Route(path = Constants.Page.FRAGMENT_AUTHOR_ARTICLE)
class AuthorArticleFragment : BaseMVVMFragment<FragmentAuthorArticleBinding, AuthorArticleViewModel>() {

    companion object{


        fun newInstance(nickName : String) : AuthorArticleFragment {

            val fragment = AuthorArticleFragment()
            val bundle = Bundle()
            bundle.putString(Constants.SP.AUTHOR_NAME,nickName)
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




        vm.articleList.observe(viewLifecycleOwner, Observer {

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

        vm.getAuthorFromNickName(arguments!!.getString(Constants.SP.PAGE_TITLE,""))
//        vm.getAuthorArticleList(arguments!!.getInt(Constants.SP.AUTHOR_ID,-1))

    }
}