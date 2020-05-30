package com.example.login.net

import com.example.commonlibrary.entity.BaseBean
import com.example.commonlibrary.net.RetrofitManager
import com.example.login.entitiy.LoginBean
import io.reactivex.Observable

/**
 *  @author
 *
 *  @create  2020/5/29 9:45
 *
 */


object LoginRetrofitManager  {



        val loginApiService  = RetrofitManager.getRetrofit().create(LoginApiService::class.java)

        /**
         * 登录
         */
        fun loginIn(username : String,password : String) : Observable<LoginBean> {

            return loginApiService.loginIn(username,password)
        }
        /**
         * 注册
         */
        fun loginUp(username : String,password : String,repassword : String) :Observable<BaseBean>{


            return loginApiService.loginUp(username,password,repassword)
        }

        /**
         * 退出登录
         */
        fun loginOut() : Observable<BaseBean>{
            return loginApiService.loginOut()
        }


}