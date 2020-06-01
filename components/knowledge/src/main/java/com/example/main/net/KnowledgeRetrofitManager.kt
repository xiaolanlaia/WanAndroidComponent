package com.example.main.net

import com.example.commonlibrary.net.RetrofitManager
import com.example.main.entity.ArticleBean
import com.example.main.entity.KnowledgeBean
import io.reactivex.Observable

/**
 *  @author
 *
 *  @create  2020/6/1 15:05
 *
 */


object KnowledgeRetrofitManager {
    val knowledgeApiService  = RetrofitManager.getRetrofit().create(KnowledgeApiService::class.java)

    /**
     * 某个分类下文章
     */
    fun getArticleSort(cid : Int) : Observable<ArticleBean> {

        return knowledgeApiService.getArticleSort(cid)

    }

    /**
     * 获取体系列表数据
     */
    fun getSystemDataList() : Observable<KnowledgeBean>{

        return knowledgeApiService.getSystemDataList()
    }
}