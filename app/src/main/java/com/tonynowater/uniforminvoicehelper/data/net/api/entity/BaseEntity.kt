package com.tonynowater.uniforminvoicehelper.data.net.api.entity

/**
 * Created by tonyliao on 2018/3/15.
 */
abstract class BaseEntity {
    abstract val v: String
    abstract val code: Int
    abstract val msg: String
    fun is200() = code == 200
    fun msgCode() = "$msg($code)"
}