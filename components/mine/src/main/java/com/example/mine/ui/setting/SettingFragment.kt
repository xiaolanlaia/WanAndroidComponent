package com.example.mine.ui.setting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.mine.R
import com.example.mine.databinding.MineFragmentSettingBinding
import com.example.mine.ui.MineRepository
import com.example.mine.ui.MineVMFactory
import com.example.mine.ui.MineViewModel

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 8:32
 *
 */


class SettingFragment : BaseMVVMFragment<MineFragmentSettingBinding, MineViewModel>(){
    override fun initViewModel(): MineViewModel =
        ViewModelProvider(this, MineVMFactory(MineRepository())).get(MineViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.mine_fragment_setting

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm


    }

    fun initRequest(){


    }
}