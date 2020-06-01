package com.example.weichat.ui

import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.util.doInBackground
import com.example.weichat.entity.OfficialAccountBean
import com.example.weichat.net.WeicharRetrofitManager
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class WeichatRepository {
    /**
     * 获取公众号列表
     */
    fun getOfficialAccountList() : Observable<OfficialAccountBean>{
        return WeicharRetrofitManager.getOfficialAccountList().doInBackground()
    }

    /**
     * 公众号历史数据
     */
    fun getHistoryData(id : Int) : Observable<ArticleBean>{
        return WeicharRetrofitManager.getHistoryData(id).doInBackground()
    }
    /**
     * 收藏
     */
    fun collect(id : Int) : Observable<BaseBean>{

        return WeicharRetrofitManager.collect(id).doInBackground()
    }
    /**
     * 取消收藏
     */
    fun unCollect(id : Int) : Observable<BaseBean>{

        return WeicharRetrofitManager.unCollect(id).doInBackground()
    }
}