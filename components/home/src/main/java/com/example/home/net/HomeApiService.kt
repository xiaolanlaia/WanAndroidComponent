package com.example.home.net

import com.example.commonlibrary.entity.BaseBean
import com.example.home.entity.ArticleBean
import com.example.home.entity.HomeBannerBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *  @author
 *
 *  @create  2020/6/1 11:55
 *
 */


interface HomeApiService {

    /**
     * banner数据
     */
    @GET("banner/json")
    fun getHomeBannerData() : Observable<HomeBannerBean>

    /**
     * 文章列表
     */
    @GET("article/list/0/json")
    fun getHomeArticleList() : Observable<ArticleBean>

    /**
     * 广场列表
     */
    @GET("user_article/list/0/json")
    fun getSquareList() : Observable<ArticleBean>

    /**
     * 最新项目
     */
    @GET("article/listproject/0/json")
    fun getLatestProject() : Observable<ArticleBean>

    /**
     * 收藏
     */
    @POST("lg/collect/{id}/json")
    fun collect(@Path("id") id : Int) : Observable<BaseBean>
    /**
     * 取消收藏
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id : Int) : Observable<BaseBean>
}