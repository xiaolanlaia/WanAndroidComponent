package com.example.conponttest

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.example.login.LoginApplication

/**
 *  @author
 *
 *  @create  2020/5/26 16:26
 *
 */


class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        // 初始化ARouter
        ARouter.init(this)

        LoginApplication.onCreateAsLibrary()
    }

    private fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

}