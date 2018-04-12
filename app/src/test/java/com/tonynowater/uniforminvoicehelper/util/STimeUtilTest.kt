package com.tonynowater.uniforminvoicehelper.util

import com.tonynowater.uniforminvoicehelper.view.query.ECarrierQueryType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Created by tonyliao on 2018/3/30.
 */
class STimeUtilTest {
    @Test
    fun transferWeekDays() {
        var expected = ""
        assertEquals(expected, STimeUtil.transferWeekDays(-10))
        assertEquals(expected, STimeUtil.transferWeekDays(0))
        assertEquals(expected, STimeUtil.transferWeekDays(10))

        expected = "一"
        assertEquals(expected, STimeUtil.transferWeekDays(1))

        expected = "二"
        assertEquals(expected, STimeUtil.transferWeekDays(2))

        expected = "三"
        assertEquals(expected, STimeUtil.transferWeekDays(3))

        expected = "四"
        assertEquals(expected, STimeUtil.transferWeekDays(4))

        expected = "五"
        assertEquals(expected, STimeUtil.transferWeekDays(5))

        expected = "六"
        assertEquals(expected, STimeUtil.transferWeekDays(6))

        expected = "日"
        assertEquals(expected, STimeUtil.transferWeekDays(7))
    }

    @Test
    fun transferTaiwanYearToCommonEra() {
        val expected = "2018/03/30"
        assertEquals(expected, STimeUtil.transferTaiwanYearToCommonEra(107, 3, 30))
    }

    /**
     * 說明：因為每逢單月25日才會開出號碼，且格式為民國年雙數月 例:10702
     *
     * 測試案例：雙數月(二月)，2018/02/10 要取得 10610
     */
    @Test
    fun getCurrentInvoiceTerm_1() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 1)
        calendar.set(Calendar.DAY_OF_MONTH, 10)
        val date = STimeUtil.getCurrentInvoiceTerm(calendar.time)

        assertEquals(5, date.length)
        assertEquals("10612", date)
    }

    /**
     * 說明：因為每逢單月25日才會開出號碼，且格式為民國年雙數月 例:10702
     *
     * 測試案例：雙數月(不為二月)，2018/04/25 要取得 10702
     */
    @Test
    fun getCurrentInvoiceTerm_2() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 3)
        calendar.set(Calendar.DAY_OF_MONTH, 25)
        val date = STimeUtil.getCurrentInvoiceTerm(calendar.time)

        assertEquals(5, date.length)
        assertEquals("10702", date)
    }

    /**
     * 說明：因為每逢單月25日才會開出號碼，且格式為民國年雙數月 例:10702
     *
     * 測試案例：單數月已開出號碼，2018/03/25 要取得 10702
     */
    @Test
    fun getCurrentInvoiceTerm_3() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 2)
        calendar.set(Calendar.DAY_OF_MONTH, 30)
        val date = STimeUtil.getCurrentInvoiceTerm(calendar.time)

        assertEquals(5, date.length)
        assertEquals("10702", date)
    }

    /**
     * 說明：因為每逢單月25日才會開出號碼，且格式為民國年雙數月 例:10702
     *
     * 測試案例：單數月未開出號碼，2018/03/24 要取得 10612
     */
    @Test
    fun getCurrentInvoiceTerm_4() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 2)
        calendar.set(Calendar.DAY_OF_MONTH, 24)
        val date = STimeUtil.getCurrentInvoiceTerm(calendar.time)

        assertEquals(5, date.length)
        assertEquals("10612", date)
    }

    /**
     * 說明：因為每逢單月25日才會開出號碼，且格式為民國年雙數月 例:10702
     *
     * 測試案例：單數月(一月)未開出號碼，2018/01/24 要取得 10610
     */
    @Test
    fun getCurrentInvoiceTerm_5() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 0)
        calendar.set(Calendar.DAY_OF_MONTH, 24)
        val date = STimeUtil.getCurrentInvoiceTerm(calendar.time)

        assertEquals(5, date.length)
        assertEquals("10610", date)
    }

    /**
     * 測試案例：2018/01/24 THIS_MONTH 要取得本月 起始日期 2018/01/01；結束日期 2018/01/31
     */
    @Test
    fun getThisMonthDateItem() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 0)
        calendar.set(Calendar.DAY_OF_MONTH, 24)
        val dateItem = STimeUtil.getDateItemByType(ECarrierQueryType.THIS_MONTH, calendar)
        assertEquals("2018/01/01", dateItem!!.startDate)
        assertEquals("2018/01/31", dateItem!!.endDate)
    }

    /**
     * 測試案例：2018/01/24 LAST_MONTH 要取得上月 起始日期 2017/12/01；結束日期 2017/12/31
     */
    @Test
    fun getLastMonthDateItem() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 0)
        calendar.set(Calendar.DAY_OF_MONTH, 24)
        val dateItem = STimeUtil.getDateItemByType(ECarrierQueryType.LAST_MONTH, calendar)
        assertEquals("2017/12/01", dateItem!!.startDate)
        assertEquals("2017/12/31", dateItem!!.endDate)
    }

    /**
     * 測試案例：2018/04/12 PRIZE_RECORD 要取得半年前 起始日期 2017/10/11；結束日期 2018/04/12
     */
    @Test
    fun getPrizeRecordDateItem() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 3)
        calendar.set(Calendar.DAY_OF_MONTH, 12)
        val dateItem = STimeUtil.getDateItemByType(ECarrierQueryType.PRIZE_RECORD, calendar)
        assertEquals("2017/10/12", dateItem!!.startDate)
        assertEquals("2018/04/12", dateItem!!.endDate)
    }
}