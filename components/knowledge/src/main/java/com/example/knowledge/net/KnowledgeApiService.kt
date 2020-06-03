package com.example.knowledge.net

import com.example.knowledge.entity.ArticleBean
import com.example.knowledge.entity.KnowledgeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  @author
 *
 *  @create  2020/6/1 15:06
 *
 */


interface KnowledgeApiService {

    /**
     * 某个分类下文章
     */
    @GET("article/list/0/json")
    fun getArticleSort(@Query("cid") cid : Int) : Observable<ArticleBean>

    /**
     * 获取体系列表数据
     */
    @GET("tree/json")
    fun getSystemDataList() : Observable<KnowledgeBean>
}