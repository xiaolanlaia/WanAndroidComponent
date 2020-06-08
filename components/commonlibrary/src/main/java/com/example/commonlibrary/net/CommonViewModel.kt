package com.example.commonlibrary.net

import androidx.lifecycle.MutableLiveData
import com.example.commonlibrary.commoninterface.CollectInterface
import com.example.commonlibrary.util.addTo
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author
 *
 *  @create  2020/6/7 10:56
 *
 */


class CommonViewModel() {

    val co = CompositeDisposable()
    val collect = MutableLiveData<Boolean>()
    /**
     * 收藏
     */
    fun collect(id : Int){

        RetrofitManager.collect(id).subscribe({

            when(it.errorCode){

                0 ->{
                    CollectInterface.collectStateListenerInstance.setCollectState(true)

                }
            }


        },{

        }).addTo(co)
    }

    /**
     * 取消收藏
     */

    fun unCollect(id : Int){

        RetrofitManager.unCollect(id).subscribe({


            when(it.errorCode){

                0 ->{
                    CollectInterface.collectStateListenerInstance.setCollectState(false)

                }
            }


        },{

        }).addTo(co)
    }
}