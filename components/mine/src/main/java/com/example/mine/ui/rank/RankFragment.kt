package com.example.mine.ui.rank

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.commonlibrary.util.Constants
import com.example.mine.R
import com.example.mine.databinding.MineFragmentIntegralBinding
import com.example.mine.ui.MineRepository
import com.example.mine.ui.MineVMFactory
import com.example.mine.ui.MineViewModel
import com.example.mine.ui.rank.adapter.RankAdapter
import kotlinx.android.synthetic.main.mine_fragment_integral.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 8:32
 *
 */

@Route(path = Constants.Page.FRAGMENT_MINE_RANK)
class RankFragment : BaseMVVMFragment<MineFragmentIntegralBinding, MineViewModel>(){

    val rankAdapter = RankAdapter()
    override fun initViewModel(): MineViewModel =
        ViewModelProvider(this, MineVMFactory(MineRepository())).get(MineViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.mine_fragment_integral

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm
        initRequest()
        initView()
    }

    fun initView(){

        integral_recycler.layoutManager = LinearLayoutManager(context)
        integral_recycler.adapter = rankAdapter

        vm.integralRankList.observe(viewLifecycleOwner, Observer {


            rankAdapter.replaceData(it)
        })
    }

    fun initRequest(){

        vm.getIntegralRank()
    }
}