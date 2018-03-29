package com.tonynowater.uniforminvoicehelper.data.net.api.entity

/**
 * 載具發票表頭查詢Entity
 *
 * {"v":"0.3","code":200,"msg":"執行成功","onlyWinningInv":"N","details":[{"rowNum":1,"invNum":"BD42245191","cardType":"3J0002","cardNo":"/W6MU8BQ","sellerName":"統一超商股份有限公司台中縣第二０一分公司","invStatus":"2","invDonatable":true,"amount":16,"invPeriod":"10704","donateMark":0,"invDate":{"year":107,"month":3,"date":10,"day":6,"hours":0,"minutes":0,"seconds":0,"time":1520611200000,"timezoneOffset":-480},"sellerBan":"27233523","sellerAddress":"台中市大里區西榮里益民路二段137號","invoiceTime":"19:11:22"},{"rowNum":2,"invNum":"BE00044328","cardType":"3J0002","cardNo":"/W6MU8BQ","sellerName":"統一超商股份有限公司台中市第三三三分公司","invStatus":"2","invDonatable":true,"amount":69,"invPeriod":"10704","donateMark":0,"invDate":{"year":107,"month":3,"date":17,"day":6,"hours":0,"minutes":0,"seconds":0,"time":1521216000000,"timezoneOffset":-480},"sellerBan":"53672174","sellerAddress":"台中市大里區國光路二段257號","invoiceTime":"10:40:07"},{"rowNum":3,"invNum":"BF86939590","cardType":"3J0002","cardNo":"/W6MU8BQ","sellerName":"統一超商股份有限公司台中市第三二八分公司","invStatus":"2","invDonatable":true,"amount":68,"invPeriod":"10704","donateMark":0,"invDate":{"year":107,"month":3,"date":19,"day":1,"hours":0,"minutes":0,"seconds":0,"time":1521388800000,"timezoneOffset":-480},"sellerBan":"53666383","sellerAddress":"台中市西屯區台灣大道三段688號690號","invoiceTime":"18:25:24"}]}
 *
 * Created by tonyliao on 2018/3/14.
 */
data class SCarrierInvoiceHeaderEntity(
        override val v: String
        , override val code: Int
        , override val msg: String
        , val onlyWinningInv: String
        , val details: List<SCarrierInvoiceHeaderEntityDetail>
) : BaseEntity()

data class SCarrierInvoiceHeaderEntityDetail(
        val rowNum: Int,
        val invNum: String,
        val cardType: String,
        val cardNo: String,
        val sellerName: String,
        val invStatus: String,
        val invDonatable: Boolean,
        val amount: Int,
        val invPeriod: String,
        val donateMark: Int,
        val invDate: SCarrierInvoiceHeaderEntityDetailInvDate,
        val sellerBan: String,
        val sellerAddress: String,
        val invoiceTime: String
)

data class SCarrierInvoiceHeaderEntityDetailInvDate(
        val year: Int,
        val month: Int,
        val date: Int,
        val day: Int,
        val hours: Int,
        val minutes: Int,
        val seconds: Int,
        val time: Long,
        val timezoneOffset: Int
)