package com.tonynowater.uniforminvoicehelper.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tonyliao on 2018/3/15.
 */
object STimeUtil {
    private const val TAIWAN_YEAR_BEGINNING = 1911
    private val calender = Calendar.getInstance(TimeZone.getDefault(), Locale.TAIWAN)
    private val dateformat = SimpleDateFormat("yyyy/MM/dd")

    fun expTimeStamp(): Long {
        calender.timeInMillis = System.currentTimeMillis()
        calender.add(Calendar.MINUTE, 1)
        return calender.timeInMillis
    }

    fun timeStamp(): Long {
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

    /** 民國日期轉西元日期 yyyy/MM/dd */
    fun transferTaiwanYearToCommonEra(year: Int, month: Int, date: Int): String {
        calender.set(Calendar.YEAR, TAIWAN_YEAR_BEGINNING)
        calender.add(Calendar.YEAR, year)
        calender.set(Calendar.MONTH, month - 1)//1月是0，所以要減1
        calender.set(Calendar.DAY_OF_MONTH, date)
        return dateformat.format(calender.time)
    }
}
