package com.example.login.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlibrary.base.BaseActivity
import com.example.login.R
import com.example.login.ui.fragment.LoginFragment
import com.example.login.ui.fragment.LogonFragment
import com.example.login.util.LoginConstants
import kotlinx.android.synthetic.main.activity_transfer.*

@Route(path = "/login/LoginTransferActivity")
class LoginTransferActivity : BaseActivity() {

    var type = 0

    override fun isLightStatus(): Boolean = true

    override fun initContentViewID(): Int = R.layout.activity_transfer

    override fun fitTransparentStatus() {
        info_toolbar.fitTransparentStatus()
    }

    override fun onViewCreated() {
        super.onViewCreated()

        type = intent.getIntExtra(LoginConstants.SP.TITLE_ACTIVITY_TYPE, 0)
        val transaction = supportFragmentManager.beginTransaction()

        when(type){

            TYPE_LOGIN ->{
                info_toolbar.setTitle(resources.getString(R.string.title_login))
                transaction.replace(R.id.info_content, LoginFragment()).commit()
            }

            TYPE_LOGON ->{
                info_toolbar.setTitle(resources.getString(R.string.title_logon))
                transaction.replace(R.id.info_content, LogonFragment()).commit()
            }
        }
    }

    /**
     * 内容
     */
    companion object {


        const val TYPE_LOGIN = 0
        const val TYPE_LOGON = 1


    }

}
