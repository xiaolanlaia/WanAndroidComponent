package com.example.login.ui.fragment

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.commonlibrary.base.BaseMVVMFragment
import com.example.login.R
import com.example.login.databinding.FragmentLoginBinding
import com.example.login.ui.LoginRepository
import com.example.login.ui.LoginVMFactory
import com.example.login.ui.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseMVVMFragment<FragmentLoginBinding, LoginViewModel>() {


    override fun initViewModel(): LoginViewModel =ViewModelProvider(this,
        LoginVMFactory(LoginRepository())
    ).get(LoginViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        login_password.transformationMethod = PasswordTransformationMethod.getInstance()
    }
}
