package com.example.mine.ui.integral

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.mine.R
import com.example.mine.databinding.MineFragmentIntegralBinding
import com.example.mine.ui.MineRepository
import com.example.mine.ui.MineVMFactory
import com.example.mine.ui.MineViewModel
import com.example.mine.ui.integral.adapter.IntegralAdapter
import kotlinx.android.synthetic.main.mine_fragment_integral.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 8:32
 *
 */


class IntegralFragment : BaseMVVMFragment<MineFragmentIntegralBinding, MineViewModel>() {

    val integralAdapter = IntegralAdapter()
    override fun initViewModel(): MineViewModel =
        ViewModelProvider(this, MineVMFactory(MineRepository())).get(MineViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.mine_fragment_integral

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm
        initRequest()
        initView()
    }

    fun initRequest(){
        vm.getIntegralList()
    }

    fun initView(){

        integral_recycler.layoutManager = LinearLayoutManager(context)
        integral_recycler.adapter = integralAdapter


        vm.integralList.observe(viewLifecycleOwner, Observer {

            when(it.size){

                0 ->{
                    integral_null.visibility = View.VISIBLE
                }
                else ->{
                    integralAdapter.replaceData(it)

                }
            }
        })
    }
}