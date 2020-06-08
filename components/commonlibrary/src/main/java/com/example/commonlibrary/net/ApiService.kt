package com.example.commonlibrary.net

import com.example.commonlibrary.entity.BaseBean
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *  @author
 *
 *  @create  2020/6/7 10:05
 *
 */


interface ApiService {

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