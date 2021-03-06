package com.example.login.util

import com.example.commonlibrary.BuildConfig


/**
 * 常量
 */
internal interface LoginConstants {


    /**
     * SharedPreference相关
     */
    object SP {
        //TitleWithContentActivity内容的类型
        const val TITLE_ACTIVITY_TYPE = "content_type"
        //用户是否登陆
        const val IS_LOGIN = "is_login"
        //Token
        const val TOKEN = "token"
        //
        const val URL = "url"
        const val WEBVIEW_TITLE = "webview_title"
        const val AUTHOR_ID = "user_id"
        const val AUTHOR_NAME = "user_name"
        const val ARTICLE_TITLE = "article_title"
        const val CID = "cid"



    }

    /**
     * 跟数据相关
     */
    object DATA {

        const val LOGIN_FAIL = -1001
    }


    object Page {

        const val PAGE_AUTHENTICATION = "page_authentication"

    }


}
