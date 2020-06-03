package com.example.knowledge.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.knowledge.R
import com.example.knowledge.entity.KnowledgeBean
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/11 9:36
 *
 */


class KnowledgeAdapter : BaseQuickAdapter<KnowledgeBean.DataBean,BaseViewHolder>(R.layout.knowledge_fragment_item){

    lateinit var view : View

    override fun convert(helper: BaseViewHolder, item: KnowledgeBean.DataBean) {

        helper.setText(R.id.item_title, item.name)
        val tagLayout = helper.getView<TagFlowLayout>(R.id.item_tag)
        tagLayout.adapter =
            object : TagAdapter<KnowledgeBean.DataBean.childrenBean>(item.children){
                override fun getView(
                    parent: FlowLayout?,
                    position: Int,
                    t: KnowledgeBean.DataBean.childrenBean?
                ): View {
                    val tagView : TextView =
                        LayoutInflater.from(mContext).inflate(R.layout.knowledge_fragment_item_item, parent,false) as TextView
                    tagView.text = item.children!![position].name
//                    tagView.setTextColor(ColorUtil.randomColor())
                    return tagView

                }

            }

        //todo
        tagLayout.setOnTagClickListener { view, position, parent ->
//            view.context.startActivity<TitleWithContentActivity>(
//                Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_ARTICLE_SORT_LIST),
//                Pair(Constants.SP.ARTICLE_TITLE, item.children!![position].name),
//                Pair(Constants.SP.CID, item.children!![position].id)
//            )
            false
        }
//        tagLayput.setOnSelectListener(TagFlowLayout.OnSelectListener {
//                selectPosSet -> LogUtil.e("选中了tag:$selectPosSet")
//        })
//        articleLatestProjectAdapter.setSelectedList(0)//默认选中第一个





    }

}