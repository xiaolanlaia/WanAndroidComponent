package com.example.weichat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commonlibrary.commoninterface.CollectInterface
import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.util.addTo
import com.example.weichat.entity.OfficialAccountBean
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class WeichatViewModel(val repository: WeichatRepository) : ViewModel() {

    val co = CompositeDisposable()
    val articleList = MutableLiveData<List<OfficialAccountBean.dataBean>>()
    val historyList = MutableLiveData<List<ArticleBean.DataBean.DatasBean>>()

    /**
     * 获取公众号列表
     */
    fun getOfficialAccountList(){

        repository.getOfficialAccountList().subscribe({
            when(it.errorCode){

                0 ->{
                    articleList.value = it.data

                }
            }


        },{

        }).addTo(co)
    }

    /**
     * 公众号历史数据
     */
    fun getHistoryData(id : Int){

        repository.getHistoryData(id).subscribe({
            when(it.errorCode){

                0 ->{
                    historyList.value = it.data!!.datas

                }
            }


        },{}).addTo(co)
    }

    /**
     * 收藏
     */
    fun collect(id : Int){

        repository.collect(id).subscribe({

            when(it.errorCode){

                0 ->{
                    CollectInterface.collectStateListenerInstance.setCollectState(false)
                }
            }


        },{

        }).addTo(co)
    }

    /**
     * 取消收藏
     */
    fun unCollect(id : Int){

        repository.unCollect(id).subscribe({

            when(it.errorCode){

                0 ->{
                    CollectInterface.collectStateListenerInstance.setCollectState(false)
                }
            }


        },{

        }).addTo(co)
    }
}