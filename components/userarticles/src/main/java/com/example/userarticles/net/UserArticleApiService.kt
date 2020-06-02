package com.example.userarticles.net

import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.entity.BaseBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *  @author
 *
 *  @create  2020/6/2 10:00
 *
 */


interface UserArticleApiService {

    /**
     * 作者文章列表
     */
    @GET("user/{AUTHOR_ID}/share_articles/1/json")
    fun getAuthorArticleList(@Path("AUTHOR_ID") AUTHOR_ID : Int) : Observable<ArticleBean>

    /**
     * 按作者的昵称搜索文章
     */
    @GET("/article/list/0/json")
    fun getAuthorFromNickName(@Query("author")nickName : String) : Observable<ArticleBean>

    /**
     * 某个分类下文章
     */
    @GET("article/list/0/json")
    fun getArticleSort(@Query("cid") cid : Int) : Observable<ArticleBean>

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