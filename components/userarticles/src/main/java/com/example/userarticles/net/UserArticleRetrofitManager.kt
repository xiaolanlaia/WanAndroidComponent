package com.example.userarticles.net

import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.net.RetrofitManager
import io.reactivex.Observable

/**
 *  @author
 *
 *  @create  2020/6/2 10:00
 *
 */


object UserArticleRetrofitManager {

    val userArticleApiService  = RetrofitManager.getRetrofit().create(UserArticleApiService::class.java)

    /**
     * 作者文章列表
     */
    fun getAuthorArticleList(AUTHOR_ID : Int) : Observable<ArticleBean> {

        return userArticleApiService.getAuthorArticleList(AUTHOR_ID)
    }

    /**
     * 按作者的昵称搜索文章
     */
    fun getAuthorFromNickName(nickName : String) : Observable<ArticleBean> {

        return userArticleApiService.getAuthorFromNickName(nickName)
    }

    /**
     * 某个分类下文章
     */
    fun getArticleSort(cid : Int) : Observable<ArticleBean> {

        return userArticleApiService.getArticleSort(cid)

    }

    /**
     * 收藏
     */
    fun collect(id : Int) : Observable<BaseBean>{

        return userArticleApiService.collect(id)
    }
    /**
     * 取消收藏
     */
    fun unCollect(id : Int) : Observable<BaseBean>{

        return userArticleApiService.unCollect(id)
    }
}