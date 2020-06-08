package com.example.commonlibrary.commoninterface

/**
 *  @author
 *
 *  @create  2020/6/7 11:22
 *
 */


object CollectInterface {

    lateinit var  collectStateListenerInstance : CollectStateListener


    fun setCollectStateListener(collectStateListener: CollectStateListener){
        this.collectStateListenerInstance = collectStateListener

    }

    interface CollectStateListener{
        fun setCollectState(isCollect : Boolean)

    }

}