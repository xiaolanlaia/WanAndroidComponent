package com.example.weichat.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibrary.adapter.CommonArticleAdapter
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.weichat.R
import com.example.weichat.databinding.FragmentOfficialAccountArticleBinding
import kotlinx.android.synthetic.main.fragment_official_account_article.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 8:22
 *
 */


class WeichatArticleFragment : BaseMVVMFragment<FragmentOfficialAccountArticleBinding, WeichatViewModel>() {

    companion object {
        fun newInstance(id : Int) : WeichatArticleFragment {
            val bundle = Bundle()
            bundle.putInt("id", id)
            val fragment = WeichatArticleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun initViewModel(): WeichatViewModel =
        ViewModelProvider(this, WeichatVMFactory(WeichatRepository())).get(
            WeichatViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_official_account_article
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm
        initData()
        initRequest()
    }

    fun initRequest(){
        vm.getHistoryData(arguments!!.getInt("id"))
    }

    fun initData(){
        val commonArticleAdapter = CommonArticleAdapter()
        //设置layoutManager
        official_account_article_recycler.layoutManager = LinearLayoutManager(context)
        official_account_article_recycler.adapter = commonArticleAdapter
        vm.historyList.observe(viewLifecycleOwner, Observer {
            commonArticleAdapter.replaceData(it)
        })

        commonArticleAdapter.setOnItemClickListener(object: CommonArticleAdapter.OnItemClickListener {
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

            //todo
            override fun onItemClick(view: View, name: String?, id: Int?, link: String?, title: String?) {

//                    view.context.startActivity<TitleWithContentActivity>(
//                        Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
//                        Pair(Constants.SP.URL,link),
//                        Pair(Constants.SP.WEBVIEW_TITLE,title)
//                    )
                }
            })
    }
}