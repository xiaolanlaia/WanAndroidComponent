package com.example.commonlibrary

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

/**
 *  @author
 *
 *  @create  2020/5/28 17:26
 *
 */


open class CommonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        // 初始化ARouter
        ARouter.init(this)
        //初始化全局Context
        commonContext = this.applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var commonContext: Context
    }
}