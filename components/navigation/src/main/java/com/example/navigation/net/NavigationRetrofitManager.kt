package com.example.navigation.net

import com.example.commonlibrary.net.RetrofitManager
import com.example.navigation.entity.NavArticleBean
import io.reactivex.Observable

/**
 *  @author
 *
 *  @create  2020/6/1 16:32
 *
 */


object NavigationRetrofitManager {

    val navigationApiService  = RetrofitManager.getRetrofit().create(NavigationApiService::class.java)

    /**
     * 导航数据
     */
    fun getNavigationData() : Observable<NavArticleBean> {

        return navigationApiService.getNavigationData()
    }
}