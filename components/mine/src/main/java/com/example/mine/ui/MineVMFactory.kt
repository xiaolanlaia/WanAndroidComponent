package com.example.mine.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class MineVMFactory(val repository: MineRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MineViewModel(repository) as T
    }
}