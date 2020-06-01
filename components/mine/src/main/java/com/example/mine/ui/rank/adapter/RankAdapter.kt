package com.example.mine.ui.rank.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.mine.R
import com.example.mine.entity.IntegralRankBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 9:29
 *
 */


class RankAdapter : BaseQuickAdapter<IntegralRankBean.dataBean.datasBean,BaseViewHolder>(R.layout.mine_fragment_item_item){
    override fun convert(helper: BaseViewHolder, item: IntegralRankBean.dataBean.datasBean) {
        helper.setText(R.id.item_content,"${helper.adapterPosition + 1}. ${item.username}  积分：${item.coinCount}  等级：${item.level}")

    }
}