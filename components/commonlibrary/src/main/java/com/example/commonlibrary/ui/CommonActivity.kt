package com.example.commonlibrary.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibrary.R
import com.example.commonlibrary.base.BaseActivity
import com.example.commonlibrary.util.Constants
import kotlinx.android.synthetic.main.activity_common.*

/**
 *  @author
 *
 *  @create  2020/6/4 14:26
 *
 */

@Route(path = Constants.Page.ACTIVITY_COMMON)
class CommonActivity : BaseActivity() {



    var url : String? = null

    override fun initContentViewID(): Int = R.layout.activity_common

    override fun isLightStatus(): Boolean = true

    override fun fitTransparentStatus() {
        info_toolbar.fitTransparentStatus()
    }

    override fun onViewCreated() {
        super.onViewCreated()

        info_toolbar.setTitle(intent.getStringExtra(Constants.SP.PAGE_TITLE))

        val type = intent.getIntExtra(Constants.SP.TITLE_ACTIVITY_TYPE,0)

        val url = intent.getStringExtra(Constants.SP.PAGE_URL)



        val transaction = supportFragmentManager.beginTransaction()

        when(type){


            TYPE_WEB_VIEW ->{

                transaction.replace(R.id.info_content, WebFragment.newInstance(url)).commit()
            }
        }
    }


    /**
     * 内容
     */
    companion object {

        const val TYPE_WEB_VIEW = 0


    }
}