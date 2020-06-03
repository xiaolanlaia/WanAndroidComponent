package com.example.knowledge.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.knowledge.R
import com.example.knowledge.databinding.KnowledgeFragmentBinding
import com.example.knowledge.ui.adapter.KnowledgeAdapter
import kotlinx.android.synthetic.main.knowledge_fragment.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class KnowledgeFragment : BaseMVVMFragment<KnowledgeFragmentBinding, KnowledgeViewModel>() {
    override fun initViewModel(): KnowledgeViewModel =
        ViewModelProvider(this, KnowledgeVMFactory(KnowledgeRepository())).get(KnowledgeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.knowledge_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm
        initData()
        initRequest()

    }

    fun initRequest(){
        vm.getSystemDataList()
    }

    fun initData(){

        val knowledgeAdapter = KnowledgeAdapter()
        knowledge_recycler.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        knowledge_recycler.adapter = knowledgeAdapter

        vm.dataList.observe(this, Observer {
            knowledgeAdapter.replaceData(it)
        })

    }

}