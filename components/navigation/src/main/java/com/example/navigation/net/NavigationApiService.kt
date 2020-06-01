package com.example.navigation.net

import com.example.navigation.entity.NavArticleBean
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *  @author
 *
 *  @create  2020/6/1 16:32
 *
 */


interface NavigationApiService {

    /**
     * 导航数据
     */
    @GET("navi/json")
    fun getNavigationData() : Observable<NavArticleBean>
}