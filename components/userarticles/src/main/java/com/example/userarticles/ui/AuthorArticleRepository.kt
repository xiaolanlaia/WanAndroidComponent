package com.example.userarticles.ui

import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.util.doInBackground
import com.example.userarticles.net.UserArticleRetrofitManager
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 8:59
 *
 */


class AuthorArticleRepository {

    /**
     * 作者文章列表
     */
    fun getAuthorArticleList(AUTHOR_ID : Int) : Observable<ArticleBean> {

        return UserArticleRetrofitManager.getAuthorArticleList(AUTHOR_ID).doInBackground()
    }

    /**
     * 按作者的昵称搜索文章
     */
    fun getAuthorFromNickName(nickName : String) : Observable<ArticleBean>{

        return UserArticleRetrofitManager.getAuthorFromNickName(nickName).doInBackground()
    }

    /**
     * 某个分类下文章
     */
    fun getArticleSort(cid : Int) : Observable<ArticleBean>{

        return UserArticleRetrofitManager.getArticleSort(cid).doInBackground()

    }

    /**
     * 收藏
     */
    fun collect(id : Int) : Observable<BaseBean>{

        return UserArticleRetrofitManager.collect(id).doInBackground()
    }
    /**
     * 取消收藏
     */
    fun unCollect(id : Int) : Observable<BaseBean>{

        return UserArticleRetrofitManager.unCollect(id).doInBackground()
    }

}