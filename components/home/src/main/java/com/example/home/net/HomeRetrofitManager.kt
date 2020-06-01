package com.example.home.net

import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.net.RetrofitManager
import com.example.commonlibrary.entity.ArticleBean
import com.example.home.entity.HomeBannerBean
import io.reactivex.Observable

/**
 *  @author
 *
 *  @create  2020/6/1 11:53
 *
 */


object HomeRetrofitManager  {
    val homeApiService  = RetrofitManager.getRetrofit().create(HomeApiService::class.java)

    /**
     * banner数据
     */
    fun getHomeBannerData() : Observable<HomeBannerBean> {

        return homeApiService.getHomeBannerData()
    }

    /**
     * 文章列表
     */
    fun getHomeArticleList() : Observable<ArticleBean> {

        return homeApiService.getHomeArticleList()
    }

    /**
     * 广场列表
     */
    fun getSquareList() : Observable<ArticleBean> {

        return homeApiService.getSquareList()
    }

    /**
     * 最新项目
     */
    fun getLatestProject() : Observable<ArticleBean> {

        return homeApiService.getLatestProject()
    }

    /**
     * 收藏
     */
    fun collect(id : Int) : Observable<BaseBean> {

        return homeApiService.collect(id)
    }
    /**
     * 取消收藏
     */
    fun unCollect(id : Int) : Observable<BaseBean> {

        return homeApiService.unCollect(id)
    }
}