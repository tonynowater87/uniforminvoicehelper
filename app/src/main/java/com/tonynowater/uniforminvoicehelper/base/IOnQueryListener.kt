package com.tonynowater.uniforminvoicehelper.base

/**
 * Created by tonyliao on 2018/3/15.
 */
interface IOnQueryListener<in T> {
    fun onSuccess(entity: T)
    fun onFailure(throwable: Throwable)
}