package com.tonynowater.uniforminvoicehelper.api.entity

/**
 * Created by tonyliao on 2018/3/15.
 */
open class BaseEntity {
    var v: String? = null
    var code: Int = 0
    var msg: String? = null
    fun is200() = code == 200
    fun msgCode() = "$msg($code)"
}