package com.example.home.ui

import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.util.doInBackground
import com.example.home.entity.ArticleBean
import com.example.home.entity.HomeBannerBean
import com.example.home.net.HomeRetrofitManager
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class HomeRepository {

    /**
     * banner数据
     */
    fun getHomeBannerData() : Observable<HomeBannerBean>{

        return HomeRetrofitManager.getHomeBannerData().doInBackground()
    }
    /**
     * 文章列表
     */
    fun getHomeArticleList() : Observable<ArticleBean>{

        return HomeRetrofitManager.getHomeArticleList().doInBackground()
    }
    /**
     * 广场列表
     */
    fun getSquareList() : Observable<ArticleBean>{

        return HomeRetrofitManager.getSquareList().doInBackground()
    }

    /**
     * 最新项目
     */
    fun getLatestProject() : Observable<ArticleBean>{

        return HomeRetrofitManager.getLatestProject().doInBackground()
    }

    /**
     * 收藏
     */
    fun collect(id : Int) : Observable<BaseBean>{

        return HomeRetrofitManager.collect(id).doInBackground()
    }
    /**
     * 取消收藏
     */
    fun unCollect(id : Int) : Observable<BaseBean>{

        return HomeRetrofitManager.unCollect(id).doInBackground()
    }


}