package com.example.mine.ui.integral.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.mine.R
import com.example.mine.entity.IntegralListBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 9:02
 *
 */


class IntegralAdapter : BaseQuickAdapter<IntegralListBean.dataBean.datasBean,BaseViewHolder>(R.layout.mine_fragment_item_item) {
    override fun convert(helper: BaseViewHolder, item: IntegralListBean.dataBean.datasBean) {
        helper.setText(R.id.item_content,item.desc)
    }
}