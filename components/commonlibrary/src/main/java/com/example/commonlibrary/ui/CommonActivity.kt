package com.example.commonlibrary.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibrary.R
import com.example.commonlibrary.base.BaseActivity
import com.example.commonlibrary.router.MyARouter
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
        val id = intent.getIntExtra(Constants.SP.ID,0)

        when(type){


            TYPE_WEB_VIEW ->{

                transaction.replace(R.id.info_content, WebFragment.newInstance(url)).commit()
            }

            TYPE_ARTICLE_SORT_FRAGMENT ->{



                val articleSortFragment = MyARouter.getFragment(Constants.Page.FRAGMENT_ARTICLE_SORT,intent.getStringExtra(Constants.SP.PAGE_TITLE),id)
                transaction.replace(R.id.info_content, articleSortFragment).commit()
            }

            TYPE_AUTHOR_ARTICLE_FRAGMENT ->{

                val authorArticleFragment =
                    MyARouter.getFragment(Constants.Page.FRAGMENT_AUTHOR_ARTICLE, intent.getStringExtra(Constants.SP.PAGE_TITLE))
                transaction.replace(R.id.info_content, authorArticleFragment).commit()

            }

            TYPE_FRAGMENT_MINE_COLLECT ->{
                info_toolbar.setTitle(getString(R.string.mine_title_collect))

                val collectFragment =  MyARouter.getFragment(Constants.Page.FRAGMENT_MINE_COLLECT)
                transaction.replace(R.id.info_content, collectFragment).commit()

            }


            TYPE_FRAGMENT_MINE_INTEGRAL ->{
                info_toolbar.setTitle(getString(R.string.mine_title_integral))

                val integralFragment =  MyARouter.getFragment(Constants.Page.FRAGMENT_MINE_INTEGRAL)
                transaction.replace(R.id.info_content, integralFragment).commit()
            }


            TYPE_FRAGMENT_MINE_RANK ->{

                info_toolbar.setTitle(getString(R.string.mine_title_rank))
                val rankFragment =  MyARouter.getFragment(Constants.Page.FRAGMENT_MINE_RANK)
                transaction.replace(R.id.info_content, rankFragment).commit()
            }


            TYPE_FRAGMENT_MINE_SETTING ->{

                info_toolbar.setTitle(getString(R.string.mine_title_setting))
                val settingFragment =  MyARouter.getFragment(Constants.Page.FRAGMENT_MINE_SETTING)
                transaction.replace(R.id.info_content, settingFragment).commit()
            }

        }
    }


    /**
     * 内容
     */
    companion object {

        const val TYPE_WEB_VIEW = 0
        const val TYPE_ARTICLE_SORT_FRAGMENT = 1
        const val TYPE_AUTHOR_ARTICLE_FRAGMENT = 2

        const val TYPE_FRAGMENT_MINE_COLLECT = 3
        const val TYPE_FRAGMENT_MINE_INTEGRAL = 4
        const val TYPE_FRAGMENT_MINE_RANK = 5
        const val TYPE_FRAGMENT_MINE_SETTING = 6




    }
}