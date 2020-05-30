package com.example.login.ui

import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.net.RetrofitManager
import com.example.commonlibrary.util.doInBackground
import com.example.login.entitiy.LoginBean
import com.example.login.net.LoginRetrofitManager
import io.reactivex.Observable

/**
 *  @author
 *
 *  @create  2020/5/28 15:20
 *
 */


class LoginRepository {
    /**
     * 登录
     */
    fun loginIn(username : String,password : String) : Observable<LoginBean> {

        return LoginRetrofitManager.loginIn(username,password).doInBackground()
    }

    /**
     * 注册
     */
    fun loginUp(username : String,password : String,repassword : String) : Observable<BaseBean> {

        return LoginRetrofitManager.loginUp(username,password,repassword).doInBackground()
    }

    /**
     * 退出登录
     */
    fun loginOut() : Observable<BaseBean> {
        return LoginRetrofitManager.loginOut().doInBackground()
    }
}