package com.tonynowater.uniforminvoicehelper.view.query

import com.tonynowater.uniforminvoicehelper.util.STimeUtil

/**
 * Created by tonyliao on 2018/4/10.
 */
enum class ECarrierQueryType {
    THIS_MONTH, LAST_MONTH, CUSTOM_MONTH, PRIZE_RECORD;

    fun getDateItem(): STimeUtil.DateItem? = STimeUtil.getDateItemByType(this)
}