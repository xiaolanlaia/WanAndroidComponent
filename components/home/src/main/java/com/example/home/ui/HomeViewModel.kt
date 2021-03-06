package com.example.home.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commonlibrary.commoninterface.CollectInterface
import com.example.commonlibrary.util.CodeUtil.checkIsLogin
import com.example.commonlibrary.util.Constants
import com.example.commonlibrary.util.addTo
import com.example.commonlibrary.util.toast
import com.example.commonlibrary.entity.ArticleBean
import com.example.home.entity.HomeBannerBean
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class HomeViewModel(val repository: HomeRepository) : ViewModel() {

    val co = CompositeDisposable()

    val bannerData = MutableLiveData<List<HomeBannerBean.BannerData>>()
    val articleList = MutableLiveData<List<ArticleBean.DataBean.DatasBean>>()
    val projectArticleList = MutableLiveData<List<ArticleBean.DataBean.DatasBean>>()

    fun getHomeBannerData(context: Context){

        repository.getHomeBannerData().subscribe({

            when(it.errorCode){

                0 ->{

                    bannerData.value = it.data
                }

                Constants.DATA.LOGIN_FAIL ->{

                    checkIsLogin(context)
                }

                else ->{

                    toast(context,it.errorMsg)
                }
            }
        },{

        }).addTo(co)
    }

    /**
     * 首页文章列表
     */
    fun getHomeArticleList(){

        repository.getHomeArticleList().subscribe({

            when(it.errorCode){

                0 ->{
                    articleList.value = it.data!!.datas
                }
            }

        },{

        }).addTo(co)
    }

    /**
     * 广场列表
     */
    fun getSquareList(){

        repository.getSquareList().subscribe({

            when(it.errorCode){

                0 ->{
                    articleList.value = it.data!!.datas
                }

                else ->{}

            }

        },{

        }).addTo(co)
    }

    /**
     * 最新项目
     */
    fun getLatestProject(){

        repository.getLatestProject().subscribe({

            when(it.errorCode){

                0 ->{

                    projectArticleList.value = it.data!!.datas
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