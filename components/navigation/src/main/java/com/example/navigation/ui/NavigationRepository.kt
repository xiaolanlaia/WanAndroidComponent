package com.example.navigation.ui

import com.example.commonlibrary.util.doInBackground
import com.example.navigation.entity.NavArticleBean
import com.example.navigation.net.NavigationRetrofitManager
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 10:37
 *
 */


class NavigationRepository {
    /**
     * 导航数据
     */
    fun getNavigationData() : Observable<NavArticleBean>{

        return NavigationRetrofitManager.getNavigationData().doInBackground()
    }
}