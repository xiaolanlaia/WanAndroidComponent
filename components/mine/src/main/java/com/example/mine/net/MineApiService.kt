package com.example.mine.net

import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.entity.BaseBean
import com.example.mine.entity.IntegralBean
import com.example.mine.entity.IntegralListBean
import com.example.mine.entity.IntegralRankBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 *  @author
 *
 *  @create  2020/6/1 16:46
 *
 */


interface MineApiService {

    /**
     * 获取积分
     */
    @GET("lg/coin/userinfo/json")
    fun getIntegral() : Observable<IntegralBean>

    /**
     * 积分列表
     */
    @GET("lg/coin/list/1/json")
    fun getIntegralList() : Observable<IntegralListBean>

    /**
     * 积分排行榜
     */
    @GET("coin/rank/1/json")
    fun getIntegralRank() : Observable<IntegralRankBean>

    /**
     * 退出登录
     */
    @GET("user/logout/json")
    fun loginOut() : Observable<BaseBean>

    /**
     * 获取收藏列表
     */
    @GET("lg/collect/list/0/json")
    fun getCollectList() : Observable<ArticleBean>

    /**
     * 退出登录
     */
    @GET("user/logout/json")
    fun logout() : Observable<BaseBean>


    /**
     * 收藏
     */
    @POST("/lg/collect/add/json")
    @FormUrlEncoded
    fun addCollect(@Field("title")title : String,
                   @Field("author")author : String,
                   @Field("link")link : String) : Observable<BaseBean>

    /**
     * 取消收藏
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    fun mineUnCollect(@Path("id") id : Int,
                      @Field("originId")originId : Int) : Observable<BaseBean>
}