package com.example.mine.net

import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.net.RetrofitManager
import com.example.mine.entity.IntegralBean
import com.example.mine.entity.IntegralListBean
import com.example.mine.entity.IntegralRankBean
import io.reactivex.Observable

/**
 *  @author
 *
 *  @create  2020/6/1 16:46
 *
 */


object MineRetrofitManager {

    val mineApiService  = RetrofitManager.getRetrofit().create(MineApiService::class.java)
    /**
     * 获取积分
     */
    fun getIntegral() : Observable<IntegralBean> {
        return mineApiService.getIntegral()

    }
    /**
     * 积分列表
     */
    fun getIntegralList() : Observable<IntegralListBean> {

        return mineApiService.getIntegralList()

    }

    /**
     * 积分排行榜
     */
    fun getIntegralRank() : Observable<IntegralRankBean> {

        return mineApiService.getIntegralRank()

    }


    /**
     * 获取收藏列表
     */
    fun getCollectList() : Observable<ArticleBean> {
        return mineApiService.getCollectList()
    }

    /**
     * 退出登录
     */
    fun logout() : Observable<BaseBean>{

        return mineApiService.logout()
    }

    /**
     * 收藏
     */
    fun addCollect(title : String, author : String, link : String) : Observable<BaseBean>{

        return mineApiService.addCollect(title, author, link)
    }

    /**
     * 取消收藏
     */
    fun mineUnCollect(id : Int) : Observable<BaseBean>{

        return mineApiService.mineUnCollect(id, -1)
    }

}