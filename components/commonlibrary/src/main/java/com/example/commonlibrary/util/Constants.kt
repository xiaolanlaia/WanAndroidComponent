package com.example.commonlibrary.util

import com.example.commonlibrary.BuildConfig


/**
 * 常量
 */
object Constants {

    /**
     * 网络请求相关
     */
    object URL {
        // Base_Url
        const val BASE_URL = BuildConfig.ApiHost

    }

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

        const val MINE_NAME = "mine_name"
        const val MINE_LEVEL = "mine_level"
        const val MINE_INTEGRAL = "mine_integral"






    }

    /**
     * 跟数据相关
     */
    object DATA {

        const val LOGIN_FAIL = -1001
    }


    object Page {

        const val FRAGMENT_HOME = "/home/HomeFragment"
        const val FRAGMENT_KNOWLEDGE = "/knowledge/KnowledgeFragment"
        const val FRAGMENT_WEICHAT = "/weichat/WeichatFragment"
        const val FRAGMENT_NAVIGATION = "/navigation/NavigationFragment"
        const val FRAGMENT_MINE = "/mine/MineFragment"


    }

}
