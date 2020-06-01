package com.example.navigation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commonlibrary.util.addTo
import com.example.navigation.entity.NavArticleBean
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 10:37
 *
 */


class NavigationViewModel(val repository: NavigationRepository) : ViewModel(){

    val co = CompositeDisposable()
    val dataList = MutableLiveData<List<NavArticleBean.dataBean>>()

    /**
     * 导航数据
     */
    fun getNavigationData(){

        repository.getNavigationData().subscribe({

            when(it.errorCode){
                0 ->{
                    dataList.value = it.data

                }
            }

        },{

        }).addTo(co)
    }
}