package com.tonynowater.uniforminvoicehelper.data.net.api.entity

/**
 * {"fifthPrizeAmt":"0001000","firstPrizeAmt":"0200000","firstPrizeNo1":"03696891","firstPrizeNo10":"","firstPrizeNo2":"79882491","firstPrizeNo3":"77486437","firstPrizeNo4":"","firstPrizeNo5":"","firstPrizeNo6":"","firstPrizeNo7":"","firstPrizeNo8":"","firstPrizeNo9":"","fourthPrizeAmt":"0004000","invoYm":"10612","secondPrizeAmt":"0040000","sixthPrizeAmt":"0000200","sixthPrizeNo1":"055","sixthPrizeNo2":"816","sixthPrizeNo3":"292","sixthPrizeNo4":"","sixthPrizeNo5":"","sixthPrizeNo6":"","spcPrizeAmt":"2000000","spcPrizeNo":"67035249","spcPrizeNo2":"","spcPrizeNo3":"","superPrizeAmt":"10000000","superPrizeNo":"75350343","thirdPrizeAmt":"0010000","timeStamp":{"date":25,"day":4,"hours":14,"minutes":27,"month":0,"seconds":53,"time":1516861673000,"timezoneOffset":-480,"year":118},"updateDate":"1070125","v":"0.2","code":"200","msg":"查詢成功"}
 *
 * Created by tonyliao on 2018/3/16.
 */
data class SInvAppPrizeNumListEntity(
        override val v: String,
        override val code: Int,
        override val msg: String,
		val fifthPrizeAmt: String,
		val firstPrizeAmt: String,
		val firstPrizeNo1: String,
		val firstPrizeNo10: String,
		val firstPrizeNo2: String,
		val firstPrizeNo3: String,
		val firstPrizeNo4: String,
		val firstPrizeNo5: String,
		val firstPrizeNo6: String,
		val firstPrizeNo7: String,
		val firstPrizeNo8: String,
		val firstPrizeNo9: String,
		val fourthPrizeAmt: String,
		val invoYm: String,
		val secondPrizeAmt: String,
		val sixthPrizeAmt: String,
		val sixthPrizeNo1: String,
		val sixthPrizeNo2: String,
		val sixthPrizeNo3: String,
		val sixthPrizeNo4: String,
		val sixthPrizeNo5: String,
		val sixthPrizeNo6: String,
		val spcPrizeAmt: String,
		val spcPrizeNo: String,
		val spcPrizeNo2: String,
		val spcPrizeNo3: String,
		val superPrizeAmt: String,
		val superPrizeNo: String,
		val thirdPrizeAmt: String,
		val timeStamp: TimeStamp,
		val updateDate: String
): BaseEntity()

data class TimeStamp(
		val date: Int,
		val day: Int,
		val hours: Int,
		val minutes: Int,
		val month: Int,
		val seconds: Int,
		val time: Long,
		val timezoneOffset: Int,
		val year: Int
)