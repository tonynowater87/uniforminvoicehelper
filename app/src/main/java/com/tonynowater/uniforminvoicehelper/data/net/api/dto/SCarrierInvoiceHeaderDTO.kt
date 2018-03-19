package com.tonynowater.uniforminvoicehelper.data.net.api.dto

/**
 * Created by tonyliao on 2018/3/15.
 */
data class SCarrierInvoiceHeaderDTO(val date: String,
                                    val formatDate: String,
                                    val invoiceNo: String,
                                    val sellerName: String,
                                    val amount: String) {
    fun showedText(): String = "$date $invoiceNo $sellerName"
}