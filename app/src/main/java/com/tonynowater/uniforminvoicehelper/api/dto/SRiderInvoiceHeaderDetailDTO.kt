package com.tonynowater.uniforminvoicehelper.api.dto

/**
 * Created by tonyliao on 2018/3/15.
 */
data class SRiderInvoiceHeaderDetailDTO(var date: String, var invoiceNo: String, var sellerName: String) {
    fun showedText():String = "$date $invoiceNo $sellerName"
}