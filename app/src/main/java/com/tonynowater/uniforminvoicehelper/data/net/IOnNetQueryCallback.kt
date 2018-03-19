package com.tonynowater.uniforminvoicehelper.data.net

/**
 * Created by tonyliao on 2018/3/15.
 */
interface IOnNetQueryCallback<in T> {
    fun onSuccess(entity: T)
    fun onFailure(throwable: Throwable)
}