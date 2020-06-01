package com.example.weichat.net

import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.entity.BaseBean
import com.example.weichat.entity.OfficialAccountBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *  @author
 *
 *  @create  2020/6/1 15:29
 *
 */


interface WeicharApiService {

    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    fun getOfficialAccountList() : Observable<OfficialAccountBean>

    /**
     * 公众号历史数据
     */
    @GET("wxarticle/list/{id}/1/json")
    fun getHistoryData(@Path("id")id : Int) : Observable<ArticleBean>

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