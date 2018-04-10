package com.tonynowater.uniforminvoicehelper.util

import com.tonynowater.uniforminvoicehelper.view.query.ECarrierQueryType
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tonyliao on 2018/3/15.
 */
object STimeUtil {
    private const val TAIWAN_YEAR_BEGINNING = 1911
    private val calender = Calendar.getInstance(TimeZone.getDefault(), Locale.TAIWAN)
    private val dateformat = SimpleDateFormat("yyyy/MM/dd")
    private val dateformat_invoice_term = SimpleDateFormat("yyyyMMdd")

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

    /**
     * 取得當期統一發票的民國日期
     * 格式為民國年雙數月 例:10702
     */
    fun getCurrentInvoiceTerm(date: Date = Date()): String {
        val nowDate = dateformat_invoice_term.format(date)
        var year = nowDate.substring(0, 4)
        var month = nowDate.substring(year.length, year.length + 2)
        var iDay = nowDate.substring(year.length + month.length, year.length + month.length + 2).toInt()

        var iYear = year.toInt()
        var iMonth = month.toInt()

        iYear -= TAIWAN_YEAR_BEGINNING

        if (iMonth % 2 == 0) {
            //雙數月
            if (iMonth == 2) {
                //二月還沒開獎，取去年最後一期
                iYear -= 1
                iMonth = 12
            } else {
                //其它月還沒開獎，取前一期
                iMonth -= 2
            }
        } else {
            //單數月
            if (iDay < 25) {
                //尚未開獎
                when (iMonth) {
                    1 -> {
                        iYear -= 1
                        iMonth = 10
                    }
                    3 -> {
                        iYear -= 1
                        iMonth = 12
                    }
                    else -> {
                        iMonth -= 3
                    }
                }
            } else {
                //已開獎
                if (iMonth == 1) {
                    iYear -= 1
                    iMonth = 12
                } else {
                    iMonth -= 1
                }
            }
        }

        return if (iMonth.toString().length <= 1) {
            "${iYear}0$iMonth"
        } else {
            "$iYear$iMonth"
        }
    }

    fun getDateItemByType(carrierQueryType: ECarrierQueryType,
                          thisMonthCalendar: Calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.TAIWAN)): DateItem? {

        when (carrierQueryType) {
            ECarrierQueryType.THIS_MONTH -> {
                thisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1)
                val startDate = dateformat.format(thisMonthCalendar.time)
                thisMonthCalendar.set(Calendar.DAY_OF_MONTH, thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                val endDate = dateformat.format(thisMonthCalendar.time)
                return DateItem(startDate, endDate)
            }
            ECarrierQueryType.LAST_MONTH -> {
                thisMonthCalendar.add(Calendar.MONTH, -1)
                thisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1)
                val startDate = dateformat.format(thisMonthCalendar.time)
                thisMonthCalendar.set(Calendar.DAY_OF_MONTH, thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                val endDate = dateformat.format(thisMonthCalendar.time)
                return DateItem(startDate, endDate)
            }
            ECarrierQueryType.CUSTOM_MONTH -> {
                return null
            }

            ECarrierQueryType.PRIZE_RECORD -> {
                return null
            }
        }
    }

    data class DateItem(val startDate: String, val endDate: String)
}
