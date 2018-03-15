package com.tonynowater.uniforminvoicehelper.base

/**
 * Created by tonyliao on 2018/3/15.
 */
interface IBaseMvpView {
    fun onNoData()
    fun onSuccess()
    fun onError(msg: String)
    fun showLoading()
    fun hideLoading()
}