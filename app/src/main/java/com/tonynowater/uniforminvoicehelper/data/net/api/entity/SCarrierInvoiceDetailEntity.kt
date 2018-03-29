package com.tonynowater.uniforminvoicehelper.data.net.api.entity;

/**
 * {"v":"0.3","code":200,"msg":"執行成功","invNum":"AC28103667","invDate":"20180216","sellerName":"統一超商股份有限公司台中市第三八一分公司","amount":250,"invStatus":"2","invPeriod":"10702","details":[{"rowNum":"1","description":"(新)寶亨9號mojito雙晶球香菸","quantity":"2","unitPrice":"100","amount":200},{"rowNum":"1","description":"勁量鈕扣型鋰電池20163V1入","quantity":"1","unitPrice":"50","amount":50},{"rowNum":"1","description":"超跑點數","quantity":"1","unitPrice":"0","amount":0}],"sellerBan":"24792024","sellerAddress":"台中市大里區中興路2段480號","invoiceTime":"21:04:04"}
 * <p>
 * Created by tonyliao on 2018/3/16.
 */
data class SCarrierInvoiceDetailEntity(
        override val v: String,
        override val code: Int,
        override val msg: String,
        val invNum: String,
        val invDate: String,
        val sellerName: String,
        val amount: Int,
        val invStatus: String,
        val invPeriod: String,
        val details: List<SCarrierInvoiceDetailEntityDetail>,
        val sellerBan: String,
        val sellerAddress: String,
        val invoiceTime: String
) : BaseEntity()

data class SCarrierInvoiceDetailEntityDetail(
        val rowNum: String,
        val description: String,
        val quantity: String,
        val unitPrice: String,
        val amount: Int
)

