package com.example.mine.ui

import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.util.doInBackground
import com.example.mine.entity.IntegralBean
import com.example.mine.entity.IntegralListBean
import com.example.mine.entity.IntegralRankBean
import com.example.mine.net.MineRetrofitManager
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class MineRepository {

    /**
     * 获取积分
     */
    fun getIntegral() : Observable<IntegralBean>{
        return MineRetrofitManager.getIntegral().doInBackground()

    }
    /**
     * 积分列表
     */
    fun getIntegralList() : Observable<IntegralListBean>{

        return MineRetrofitManager.getIntegralList().doInBackground()

    }
    /**
     * 积分排行榜
     */
    fun getIntegralRank() : Observable<IntegralRankBean>{

        return MineRetrofitManager.getIntegralRank().doInBackground()

    }

    /**
     * 获取收藏列表
     */
    fun getCollectList() : Observable<ArticleBean>{
        return MineRetrofitManager.getCollectList().doInBackground()
    }

    /**
     * 退出登录
     */
    fun logout() : Observable<BaseBean>{

        return MineRetrofitManager.logout().doInBackground()
    }


    /**
     * 收藏
     */
    fun addCollect(title : String, author : String, link : String) : Observable<BaseBean>{

        return MineRetrofitManager.addCollect(title, author, link).doInBackground()
    }
    /**
     * 取消收藏
     */
    fun mineUnCollect(id : Int) : Observable<BaseBean>{

        return MineRetrofitManager.mineUnCollect(id).doInBackground()
    }
}