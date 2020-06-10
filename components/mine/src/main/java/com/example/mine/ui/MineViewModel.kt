package com.example.mine.ui

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commonlibrary.CommonApplication.Companion.commonContext
import com.example.commonlibrary.commoninterface.CollectInterface
import com.example.commonlibrary.cookie.CookieManager
import com.example.commonlibrary.entity.ArticleBean
import com.example.commonlibrary.router.MyARouter
import com.example.commonlibrary.ui.CommonActivity
import com.example.commonlibrary.util.CodeUtil.checkIsLogin
import com.example.commonlibrary.util.Constants
import com.example.commonlibrary.util.SharedHelper
import com.example.commonlibrary.util.addTo
import com.example.commonlibrary.util.toast
import com.example.mine.R
import com.example.mine.entity.IntegralListBean
import com.example.mine.entity.IntegralRankBean
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class MineViewModel(val repository: MineRepository) : ViewModel() {

    val co = CompositeDisposable()

    var account = MutableLiveData<String>(commonContext.getString(R.string.click_to_login))
    var level = MutableLiveData<String>()
    var mineIntegral = MutableLiveData<String>()


    init {
        account.value = SharedHelper.getShared().getString(Constants.SP.MINE_NAME,commonContext.getString(R.string.click_to_login))
        level.value = SharedHelper.getShared().getString(Constants.SP.MINE_LEVEL,"")
        mineIntegral.value = SharedHelper.getShared().getString(Constants.SP.MINE_INTEGRAL,"")
    }

    val collectList = MutableLiveData<List<ArticleBean.DataBean.DatasBean>>()
    val integralList = MutableLiveData<List<IntegralListBean.dataBean.datasBean>>()
    val integralRankList = MutableLiveData<List<IntegralRankBean.dataBean.datasBean>>()

    val mineClickListener = View.OnClickListener {

        when(it.id){

            R.id.top_relative ->{

                if (checkIsLogin(it.context)) return@OnClickListener

                MyARouter.openActivity(Constants.Page.ACTIVITY_LOGIN)

            }

            R.id.mine_collect_row ->{
                if (!checkIsLogin(it.context)) return@OnClickListener


                MyARouter.openActivity(
                    Constants.Page.ACTIVITY_COMMON, CommonActivity.TYPE_FRAGMENT_MINE_COLLECT)


            }
            R.id.mine_integral_row ->{
                if (!checkIsLogin(it.context)) return@OnClickListener

                MyARouter.openActivity(
                    Constants.Page.ACTIVITY_COMMON, CommonActivity.TYPE_FRAGMENT_MINE_INTEGRAL)



            }
            R.id.mine_rank_row ->{
                if (!checkIsLogin(it.context)) return@OnClickListener

                MyARouter.openActivity(
                    Constants.Page.ACTIVITY_COMMON, CommonActivity.TYPE_FRAGMENT_MINE_RANK)



            }
            R.id.mine_setting_row ->{
                if (!checkIsLogin(it.context)) return@OnClickListener

                MyARouter.openActivity(
                    Constants.Page.ACTIVITY_COMMON, CommonActivity.TYPE_FRAGMENT_MINE_SETTING)

            }

            R.id.setting_row ->{

                AlertDialog.Builder(it.context)
                    .setTitle(it.context.getString(R.string.confirm_logout))
                    .setMessage(it.context.getString(R.string.logout_tip))
                    .setPositiveButton(it.context.getString(R.string.logout_yes)) { _, _ -> logout(it.context) }
                    .setNegativeButton(it.context.getString(R.string.logout_no)) { dialog, _ -> dialog.dismiss() }
                    .show()


            }
        }

    }

    /**
     * 获取积分
     */
    fun getIntegral(){

        repository.getIntegral().subscribe({

            when(it.errorCode){

                0 ->{

                    SharedHelper.getEdit { sp -> sp.putString(Constants.SP.MINE_NAME,it.data!!.username) }
                    SharedHelper.getEdit { sp -> sp.putString(Constants.SP.MINE_LEVEL,"lv ${it.data!!.level}") }
                    SharedHelper.getEdit { sp -> sp.putString(Constants.SP.MINE_INTEGRAL,"积分：${it.data!!.coinCount}") }
                    account.value = it.data!!.username
                    level.value = "lv ${it.data!!.level}"
                    mineIntegral.value = "积分：${it.data!!.coinCount}"

                }

                else ->{
                    account.value = commonContext.getString(R.string.click_to_login)
                    level.value = ""
                    mineIntegral.value = ""
                }

            }
        },{}).addTo(co)
    }




    /**
     * 获取收藏列表
     */
    fun getCollectList(){

        repository.getCollectList().subscribe({

            when(it.errorCode){

                0 ->{
                    collectList.value = it.data!!.datas
                }

                else ->{
                    toast(commonContext,it.errorMsg)
                }
            }


        },{

        }).addTo(co)
    }

    /**
     * 我的积分
     */
    fun getIntegralList(){

        repository.getIntegralList().subscribe({

            when(it.errorCode){

                0 ->{

                    integralList.value = it.data!!.datas

                }
            }
        },{}).addTo(co)


    }

    /**
     * 积分排行榜
     */
    fun getIntegralRank(){

        repository.getIntegralRank().subscribe({

            when(it.errorCode){

                0 ->{
                    integralRankList.value = it.data!!.datas
                }
            }
        },{

        }).addTo(co)
    }

    /**
     * 退出登录
     */
    fun logout(context: Context){

        repository.logout().subscribe({

            when(it.errorCode){

                0 ->{
                    toast(commonContext,commonContext.getString(R.string.logout_success))
                    deleteData()
                    SharedHelper.getEdit { sp -> sp.putBoolean(Constants.SP.IS_LOGIN,false) }
                    CookieManager.getInstance().clearAllCookie()
                    (context as Activity).finish()

                }
            }
        },{

        }).addTo(co)
    }


    fun deleteData(){
        SharedHelper.getEdit { sp -> sp.putString(Constants.SP.MINE_NAME,commonContext.getString(R.string.click_to_login)) }
        SharedHelper.getEdit { sp -> sp.putString(Constants.SP.MINE_LEVEL,"") }
        SharedHelper.getEdit { sp -> sp.putString(Constants.SP.MINE_INTEGRAL,"") }

        account.value = commonContext.getString(R.string.click_to_login)
        level.value = ""
        mineIntegral.value = ""
    }

    /**
     * 收藏
     */
    fun addCollect(title : String, author : String, link : String){

        repository.addCollect(title, author, link).subscribe({

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
    fun mineUnCollect(id : Int){

        repository.mineUnCollect(id).subscribe({

            when(it.errorCode){

                0 ->{
                    CollectInterface.collectStateListenerInstance.setCollectState(false)
                }
            }


        },{

        }).addTo(co)
    }
}