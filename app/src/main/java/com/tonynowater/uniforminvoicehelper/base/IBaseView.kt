package com.tonynowater.uniforminvoicehelper.base

/**
 * Created by tonyliao on 2018/3/15.
 */
interface IBaseView {
    fun showSuccess()
    fun showError(msg: String)
    fun showLoading()
    fun hideLoading()
    fun showToast(msg: String)
}