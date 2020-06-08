package com.example.commonlibrary.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.commonlibrary.R
import com.example.commonlibrary.commoninterface.CollectInterface
import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.net.CommonViewModel
import com.example.commonlibrary.util.CodeUtil

/**
 * @author xiaolanlaia
 * @create 2020/4/11 9:56
 */


class CommonArticleAdapter:
    BaseQuickAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder>(R.layout.home_fragment_recycler_item) {

    val commonViewModel = CommonViewModel()
    private lateinit var onItemClickListener: OnItemClickListener


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, name : String?, id : Int?, link : String?, title : String?)
        fun onItemClick(id : Int,collect : Boolean)
    }




    override fun convert(helper: BaseViewHolder, item: ArticleBean.DataBean.DatasBean) {




        helper
            .setText(R.id.article_title, item.title)
            .setText(R.id.article_author, returnAuthor(item))
            .setText(R.id.article_chapter, item.superChapterName)
            .setText(R.id.article_time, item.niceDate)
            .setImageResource(R.id.article_collect,if (item.collect!!) R.drawable.ic_favorite_collect_24dp else R.drawable.ic_favorite_gray_24dp)


        when(item.top){

            true ->{
                helper.setGone(R.id.article_top,true)
            }

            false ->{
                helper.setGone(R.id.article_top,false)
            }
        }

        when(item.fresh){

            true ->{
                helper.setGone(R.id.article_fresh,true)
            }

            false ->{
                helper.setGone(R.id.article_fresh,false)
            }
        }

        helper.getView<TextView>(R.id.article_author).setOnClickListener {

            onItemClickListener.onItemClick(it,returnAuthor(item),item.id,item.link,item.title)
        }
        helper.getView<TextView>(R.id.article_chapter).setOnClickListener {

            onItemClickListener.onItemClick(it,item.superChapterName,item.superChapterId,item.link,item.title)
        }

        helper.getView<RelativeLayout>(R.id.article_layout).setOnClickListener {

            onItemClickListener.onItemClick(it,item.superChapterName,item.superChapterId,item.link,item.title)
        }

        helper.getView<ImageView>(R.id.article_collect).setOnClickListener {
            if (!CodeUtil.checkIsLogin(it.context)) return@setOnClickListener
            when(item.collect){

                true ->{
                    commonViewModel.unCollect(item.id!!)

                }

                false ->{
                    commonViewModel.collect(item.id!!)

                }


            }

            CollectInterface.setCollectStateListener(object : CollectInterface.CollectStateListener{
                override fun setCollectState(isCollect: Boolean) {

                    item.collect = isCollect
                    helper.setImageResource(R.id.article_collect,if (isCollect) R.drawable.ic_favorite_collect_24dp else R.drawable.ic_favorite_gray_24dp)


                }

            })


        }





    }



    fun returnAuthor(item: ArticleBean.DataBean.DatasBean) : String{

        if (!TextUtils.isEmpty(item.author)) return item.author!!
        if (!TextUtils.isEmpty(item.shareUser)) return item.shareUser!!

        return ""
    }
}

