package com.example.login

import android.app.Application

/**
 *  @author
 *
 *  @create  2020/5/28 11:03
 *
 */


class LoginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //给底层library设置context

    }
    /**
     * 作为library时需要初始化的内容
     */
    companion object {

        //内容
        fun onCreateAsLibrary(){}
    }
}