package com.tonynowater.uniforminvoicehelper.util

import java.util.*

/**
 * Created by tonyliao on 2018/3/15.
 */
object STimeUtil {
    private val calender = Calendar.getInstance(TimeZone.getDefault(), Locale.TAIWAN)

    fun expTimeStamp():Long {
        calender.timeInMillis = System.currentTimeMillis()
        calender.add(Calendar.MINUTE, 1)
        return calender.timeInMillis
    }

    fun timeStamp():Long {
        return System.currentTimeMillis()
    }

    fun transferWeekDays(day: Int): String {
        when (day) {
            1 -> return "一"
            2 -> return "二"
            3 -> return "三"
            4 -> return "四"
            5 -> return "五"
            6 -> return "六"
            7 -> return "日"
        }
        return ""
    }
}