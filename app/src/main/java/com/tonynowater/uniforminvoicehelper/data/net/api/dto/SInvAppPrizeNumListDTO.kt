package com.tonynowater.uniforminvoicehelper.data.net.api.dto

/**
 * Created by tonyliao on 2018/3/16.
 */
data class SInvAppPrizeNumListDTO(val prizeAmtList: List<String>
                                  , val superPrizeNo: String
                                  , var spcPrizeNo: List<String>
                                  , var firstPrizeNo: List<String>
                                  , var sixPrizeNo: List<String>) {
}