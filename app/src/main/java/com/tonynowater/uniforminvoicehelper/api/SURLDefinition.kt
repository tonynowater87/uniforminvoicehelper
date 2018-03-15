package com.tonynowater.uniforminvoicehelper.api

/**
 * Created by tonyliao on 2018/3/14.
 */
object SURLDefinition {

    const val VERSION = 0.3f
    const val CARDTYPE = "3J0002"

    //載具發票表頭查詢ACTION
    const val ACTION_HEADER = "carrierInvChk"
    const val ACTION_DETAIL = "carrierInvDetail"

    const val BaseTestURL = "https://www.google.com.tw/"
    const val BaseNetURL = "https://api.einvoice.nat.gov.tw/PB2CAPIVAN/"
}