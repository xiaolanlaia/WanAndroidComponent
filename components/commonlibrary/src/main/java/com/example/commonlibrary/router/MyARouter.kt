package com.example.commonlibrary.router

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlibrary.ui.CommonActivity
import com.example.commonlibrary.util.Constants

/**
 *  @author
 *
 *  @create  2020/6/3 15:29
 *
 */


object MyARouter {
    fun getArouter(): ARouter {
        return ARouter.getInstance()
    }

    fun openActivity(pathName: String) {
        getArouter().build(pathName).navigation()
    }

    fun openActivity(pathName: String,type : Int,url : String,title : String){

        getArouter().build(pathName)
            .withInt(Constants.SP.TITLE_ACTIVITY_TYPE,type)
            .withString(Constants.SP.PAGE_URL,url)
            .withString(Constants.SP.PAGE_TITLE,title)
            .navigation()
    }

    fun openActivity(pathName: String,type : Int,title : String?,id : Int){

        getArouter().build(pathName)
            .withInt(Constants.SP.TITLE_ACTIVITY_TYPE,type)
            .withString(Constants.SP.PAGE_TITLE,title)
            .withInt(Constants.SP.ID,id)
            .navigation()
    }

    fun getFragment(pathName: String): Fragment {
        return getArouter().build(pathName).navigation() as Fragment
    }

    fun getFragment(pathName: String,title : String?): Fragment {
        return getArouter().build(pathName)
            .withString(Constants.SP.PAGE_TITLE,title)
            .navigation() as Fragment
    }

    fun getFragment(pathName: String,title : String?,id : Int): Fragment {
        return getArouter().build(pathName)
            .withInt(Constants.SP.ID,id)
            .withString(Constants.SP.PAGE_TITLE,title)
            .navigation() as Fragment
    }
}