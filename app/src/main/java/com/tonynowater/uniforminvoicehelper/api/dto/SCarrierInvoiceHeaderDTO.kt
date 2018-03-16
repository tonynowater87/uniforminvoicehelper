package com.tonynowater.uniforminvoicehelper.api.dto

/**
 * Created by tonyliao on 2018/3/15.
 */
data class SCarrierInvoiceHeaderDTO(var date: String,
                                    var formatDate: String,
                                    var invoiceNo: String,
                                    var sellerName: String,
                                    var amount: String) {
    fun showedText(): String = "$date $invoiceNo $sellerName"
}