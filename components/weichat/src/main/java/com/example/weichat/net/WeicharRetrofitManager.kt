package com.example.weichat.net

import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.net.RetrofitManager
import com.example.weichat.entity.OfficialAccountBean
import io.reactivex.Observable

/**
 *  @author
 *
 *  @create  2020/6/1 15:29
 *
 */


object WeicharRetrofitManager {
    val weicharApiService  = RetrofitManager.getRetrofit().create(WeicharApiService::class.java)

    /**
     * 获取公众号列表
     */
    fun getOfficialAccountList() : Observable<OfficialAccountBean> {
        return weicharApiService.getOfficialAccountList()
    }

    /**
     * 公众号历史数据
     */
    fun getHistoryData(id : Int) : Observable<ArticleBean> {
        return weicharApiService.getHistoryData(id)
    }

    /**
     * 收藏
     */
    fun collect(id : Int) : Observable<BaseBean>{

        return weicharApiService.collect(id)
    }
    /**
     * 取消收藏
     */
    fun unCollect(id : Int) : Observable<BaseBean>{

        return weicharApiService.unCollect(id)
    }
}