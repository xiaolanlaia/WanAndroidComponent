package com.example.mine.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.commonlibrary.util.Constants
import com.example.mine.R
import com.example.mine.databinding.MineFragmentBinding

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */

@Route(path = Constants.Page.FRAGMENT_MINE)
class MineFragment : BaseMVVMFragment<MineFragmentBinding, MineViewModel>() {
    override fun initViewModel(): MineViewModel =
        ViewModelProvider(this, MineVMFactory(MineRepository())).get(MineViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.mine_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initRequest()
    }

    fun initRequest(){
        vm.getIntegral()

    }

    override fun onResume() {
        super.onResume()
        initRequest()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)


        when(hidden){


            false ->{
                initRequest()

            }
        }
    }
}