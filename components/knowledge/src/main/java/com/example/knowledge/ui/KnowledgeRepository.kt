package com.example.knowledge.ui


import com.example.commonlibrary.util.doInBackground
import com.example.knowledge.entity.ArticleBean
import com.example.knowledge.entity.KnowledgeBean
import com.example.knowledge.net.KnowledgeRetrofitManager
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class KnowledgeRepository {

    /**
     * 获取体系列表数据
     */
    fun getSystemDataList() : Observable<KnowledgeBean>{

        return KnowledgeRetrofitManager.getSystemDataList().doInBackground()
    }

    /**
     * 某个分类下文章
     */
    fun getArticleSort(cid : Int) : Observable<ArticleBean>{

        return KnowledgeRetrofitManager.getArticleSort(cid).doInBackground()

    }
}