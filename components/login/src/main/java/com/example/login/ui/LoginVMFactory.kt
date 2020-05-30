package com.example.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @author
 *
 *  @create  2020/5/28 15:21
 *
 */


class LoginVMFactory(val repository: LoginRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}