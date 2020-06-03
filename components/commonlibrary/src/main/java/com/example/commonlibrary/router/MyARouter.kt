package com.example.commonlibrary.router

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

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

    fun getFragment(pathName: String): Fragment {
        return getArouter().build(pathName).navigation() as Fragment
    }
}