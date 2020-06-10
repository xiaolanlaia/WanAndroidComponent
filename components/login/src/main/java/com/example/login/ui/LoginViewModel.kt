package com.example.login.ui

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commonlibrary.util.*
import com.example.login.R
import com.example.login.ui.activity.LoginTransferActivity
import com.example.login.util.LoginConstants
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.startActivity

/**
 *  @author
 *
 *  @create  2020/5/28 15:21
 *
 */


class LoginViewModel(val repository: LoginRepository) : ViewModel() {

    val phone = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val logonRepassword = MutableLiveData<String>()

    val co = CompositeDisposable()

    val loginClickListener = View.OnClickListener {

        when(it.id){

            R.id.logon ->{

                if (!checkLogonEmpty(it.context)) return@OnClickListener

                logon(it.context)

            }

            R.id.to_logon ->{

                it.context.startActivity<LoginTransferActivity>(
                    Pair(LoginConstants.SP.TITLE_ACTIVITY_TYPE, LoginTransferActivity.TYPE_LOGON)
                )

                (it.context as Activity).finish()

            }

            R.id.login_in ->{

                if (!checkLoginEmpty(it.context)) return@OnClickListener
                loginIn(it.context)

            }
        }
    }

    /**
     * 登录
     */
    fun loginIn(context: Context){

        repository.loginIn(phone.value!!,password.value!!).subscribe({

            when(it.errorCode){

                0 ->{

                    SharedHelper.getEdit { sp -> sp.putBoolean(Constants.SP.IS_LOGIN,true) }
                    (context as Activity).finish()

                }

                else ->{

                    toast(context,it.errorMsg)
                }

            }
        },{

        }).addTo(co)
    }
    /**
     * 注册
     */
    fun logon(context: Context){

        repository.loginUp(phone.value!!,password.value!!,logonRepassword.value!!).subscribe({

            when(it.errorCode){

                0 ->{
                    loginIn(context)
                }
            }

        },{}).addTo(co)
    }

    /**
     * 退出登录
     */
    fun loginOut(){
        repository.loginOut().subscribe({

        },{

        }).addTo(co)
    }

    /**
     * 检查手机号、密码
     */
    fun checkLoginEmpty(context: Context) : Boolean{

        if (TextUtils.isEmpty(phone.value) || TextUtils.isEmpty(password.value)) {
            toast(context,context.getString(R.string.infomation_null))
            return false
        }

        return true
    }

    /**
     * 检查确认验证码
     */
    fun checkLogonEmpty(context: Context) : Boolean{
        if (TextUtils.isEmpty(phone.value) || TextUtils.isEmpty(password.value) || TextUtils.isEmpty(logonRepassword.value)) {
            toast(context,context.getString(R.string.infomation_null))
            return false
        }

        return true
    }

    val phoneTextAfterChange = object : SimpleTextWatcher(){

        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)
            phone.value = s.toString()

        }

    }

    val passwordTextAfterChange = object : SimpleTextWatcher(){

        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)
            password.value = s.toString()

        }

    }

    val logonRepasswordChangeListener = object : SimpleTextWatcher(){

        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)
            logonRepassword.value = s.toString()

        }

    }
}