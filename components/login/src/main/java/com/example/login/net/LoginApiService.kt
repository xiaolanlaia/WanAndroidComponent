package com.example.login.net

import com.example.commonlibrary.entity.BaseBean
import com.example.login.entitiy.LoginBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *  @author
 *
 *  @create  2020/5/29 9:52
 *
 */


interface LoginApiService {

    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    fun loginIn(@Field("username")username : String,
                @Field("password")password : String) : Observable<LoginBean>

    /**
     * 退出登录
     */
    @GET("user/logout/json")
    fun loginOut() : Observable<BaseBean>

    /**
     * 注册
     */
    @POST("user/register")
    @FormUrlEncoded
    fun loginUp(@Field("username")username : String,
                @Field("password")password : String,
                @Field("repassword")repassword : String) :Observable<BaseBean>
}