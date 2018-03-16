package com.tonynowater.uniforminvoicehelper.api.dto

import android.text.TextUtils

/**
 * Created by tonyliao on 2018/3/16.
 */
data class SInvAppPrizeNumListDTO(val prizeAmtList: List<String>
                                  , val superPrizeNo: String
                                  , var spcPrizeNo: List<String>
                                  , var firstPrizeNo: List<String>
                                  , var sixPrizeNo: List<String>) {

    init {
        spcPrizeNo = transferToNonEmptyList(spcPrizeNo)
        firstPrizeNo = transferToNonEmptyList(firstPrizeNo)
        sixPrizeNo = transferToNonEmptyList(sixPrizeNo)
    }

    /**
     * 去除空值的欄位
     */
    private fun transferToNonEmptyList(originList: List<String>): List<String> {
        val nonEmptyList = mutableListOf<String>()
        originList.forEach {
            if (!TextUtils.isEmpty(it)) {
                nonEmptyList.add(it)
            }
        }
        return nonEmptyList
    }
}