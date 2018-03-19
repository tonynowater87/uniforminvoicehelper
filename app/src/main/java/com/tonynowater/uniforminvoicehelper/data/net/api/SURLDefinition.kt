package com.tonynowater.uniforminvoicehelper.data.net.api

import com.tonynowater.uniforminvoicehelper.BuildConfig
import com.tonynowater.uniforminvoicehelper.util.uuid.OpenUDID_manager

/**
 * Created by tonyliao on 2018/3/14.
 */
object SURLDefinition {

    const val INVAPP_VERSION = "0.2"
    const val CARRIER_VERSION = "0.3"
    const val CARDTYPE = "3J0002"

    //載具發票表頭查詢ACTION
    const val ACTION_HEADER = "carrierInvChk"
    const val ACTION_DETAIL = "carrierInvDetail"
    const val ACTION_QRY_WINNING_LIST = "QryWinningList"

    const val BaseTestURL = "https://www.google.com.tw/"
    const val BaseNetURL = "https://api.einvoice.nat.gov.tw/PB2CAPIVAN/"

    //手機條碼載具註冊網址
    fun getRegCarrierURL() = "$BaseNetURL/APIService/generalCarrierRegBlank?UUID=${OpenUDID_manager.getOpenUDID()}&appID=${BuildConfig.APP_ID}"
}