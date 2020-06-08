package com.example.home.ui.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.commonlibrary.commoninterface.CollectInterface
import com.example.commonlibrary.util.CodeUtil
import com.example.commonlibrary.util.GlideUtils
import com.example.home.R
import com.example.commonlibrary.entity.ArticleBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 12:26
 *
 */


class ArticleLatestProjectAdapter() : BaseQuickAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder>(R.layout.home_fragment_latest_project) {


    private lateinit var onItemClickListener : OnItemClickListener

    fun setOnItemClickListener(listener : OnItemClickListener){
        this.onItemClickListener = listener

    }
    interface OnItemClickListener{
        fun onItemClick(view : View,link : String?,title : String?)
        fun onItemClick(id : Int,collect : Boolean)
    }
    override fun convert(helper: BaseViewHolder, item: ArticleBean.DataBean.DatasBean) {

        helper
            .setText(R.id.project_title,item.title)
            .setText(R.id.project_content,item.desc)
            .setText(R.id.tv_project_author_name,returnAuthor(item))
            .setText(R.id.project_date,item.niceDate)
            .setText(R.id.project_tag,item.chapterName)
            .addOnClickListener(R.id.project_layout)
            .setImageResource(R.id.project_collect,if (item.collect!!) R.drawable.ic_favorite_collect_24dp else R.drawable.ic_favorite_gray_24dp)
            .getView<CardView>(R.id.project_layout).setOnClickListener {
                onItemClickListener.onItemClick(it,item.link,item.title)
            }

        GlideUtils.showBannerImage(mContext, helper.getView(R.id.project_preview), item.envelopePic)




        helper.getView<ImageView>(R.id.project_collect).setOnClickListener {

            if (!CodeUtil.checkIsLogin(it.context)) return@setOnClickListener

            onItemClickListener.onItemClick(item.id!!,item.collect!!)


            CollectInterface.setCollectStateListener(object : CollectInterface.CollectStateListener{
                override fun setCollectState(isCollect: Boolean) {
                    when(isCollect){

                        true ->{
                            item.collect = true
                            helper.setImageDrawable(R.id.project_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_collect_24dp))

                        }

                        false ->{
                            item.collect = false
                            helper.setImageDrawable(R.id.project_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_gray_24dp))

                        }

                    }
                }

            })


        }


    }

    fun returnAuthor(item: ArticleBean.DataBean.DatasBean) : String{

        if (!TextUtils.isEmpty(item.author)) return item.author!!

        return item.shareUser!!
    }
}