package com.example.commonlibrary.util

import android.content.SharedPreferences
import com.example.commonlibrary.CommonApplication
import org.jetbrains.anko.defaultSharedPreferences

/**
 *       Created by xiaolanlaia on 2019/5/6 13:44
 *
 *       SharedPreference 帮助工具类
 */
object SharedHelper {

    fun getEdit(edit: (editor: SharedPreferences.Editor) -> SharedPreferences.Editor) {
        edit(CommonApplication.commonContext.defaultSharedPreferences.edit()).commit()
    }

    fun getShared(): SharedPreferences {
        return CommonApplication.commonContext.defaultSharedPreferences
    }
}