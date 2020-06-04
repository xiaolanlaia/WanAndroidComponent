package com.example.mine.ui.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibrary.adapter.CommonArticleAdapter
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.commonlibrary.router.MyARouter
import com.example.commonlibrary.ui.CommonActivity
import com.example.commonlibrary.util.Constants
import com.example.mine.R
import com.example.mine.databinding.MineFragmentCollectBinding
import com.example.mine.ui.MineRepository
import com.example.mine.ui.MineVMFactory
import com.example.mine.ui.MineViewModel
import kotlinx.android.synthetic.main.mine_fragment_collect.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 16:43
 *
 */

@Route(path = Constants.Page.FRAGMENT_MINE_COLLECT)
class CollectFragment : BaseMVVMFragment<MineFragmentCollectBinding, MineViewModel>() {
    override fun initViewModel(): MineViewModel =
        ViewModelProvider(this,
            MineVMFactory(MineRepository())
        ).get(MineViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.mine_fragment_collect

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initData()
        initRequest()


    }

    fun initData(){


        val homeArticleAdapter = CommonArticleAdapter()
        //设置layoutManager
        collect_article_recycler.layoutManager = LinearLayoutManager(context)
        collect_article_recycler.adapter = homeArticleAdapter


        vm.collectList.observe(viewLifecycleOwner, Observer {

            when(it.size){

                0 ->{
                    collect_null.visibility = View.VISIBLE
                }

                else ->{
                    homeArticleAdapter.replaceData(it)

                }
            }

        })

        homeArticleAdapter.setOnItemClickListener(object: CommonArticleAdapter.OnItemClickListener {


            override fun onItemClick(id: Int, collect: Boolean) {

                when (collect) {

                    true -> {

                        vm.mineUnCollect(id)
                    }

                    false -> {
                    }
                }

            }


            override fun onItemClick(view: View, name: String?, id: Int?, link: String?, title: String?) {

                MyARouter.openActivity(
                    Constants.Page.ACTIVITY_COMMON,
                    CommonActivity.TYPE_WEB_VIEW,
                    link!!,
                    title!!)

            }
        })

    }

    fun initRequest(){

        vm.getCollectList()
    }
}