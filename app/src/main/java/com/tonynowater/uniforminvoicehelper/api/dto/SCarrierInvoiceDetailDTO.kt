package com.tonynowater.uniforminvoicehelper.api.dto

/**
 * Created by tonyliao on 2018/3/16.
 */
data class SCarrierInvoiceDetailDTO(val rowNum: Int
                                    , val description: String
                                    , val quantity: String
                                    , val unitPrice: String
                                    , val amount: Int)