package com.tonynowater.uniforminvoicehelper.data.net.api.dto

import com.tonynowater.uniforminvoicehelper.TestSApplication
import com.tonynowater.uniforminvoicehelper.data.net.api.SBaseMockJsonData
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SInvAppPrizeNumListEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by tonyliao on 2018/3/30.
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = TestSApplication::class)
class SInvAppPrizeNumListDTOTest : SBaseMockJsonData() {

    lateinit var dto: SInvAppPrizeNumListDTO
    private val REQ_INVOICE_TERM = "10706"

    @Before
    fun setUp() {
        val entity = gson.fromJson(json_prize_number_list_200, SInvAppPrizeNumListEntity::class.java)
        dto = transferUtil.transferInvPrizeNumEntityToDTO(REQ_INVOICE_TERM, entity)
    }

    @Test
    fun testInvoiceTerm() {
        assertEquals("107年5~6月", dto.getInvoiceTermText())
    }

    @Test
    fun testSuperNumber() {
        assertEquals("75350343", dto.superPrizeNo)
    }

    @Test
    fun getSpcPrizeNumbers() {
        var numbers = "67035249"
        assertEquals(numbers, dto.getSpcPrizeNumbers())
    }

    @Test
    fun getFirstPrizeNumbers() {
        var numbers = "03696891\n79882491\n77486437"
        assertEquals(numbers, dto.getFirstPrizeNumbers())
    }

    @Test
    fun getSecondToSixthPrizeNumbers() {

        var numbers = ""
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.SUPER))
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.SPECIAL))
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.FIRST))

        numbers = "3696891\n9882491\n7486437"
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.SECOND))

        numbers = "696891\n882491\n486437"
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.THIRD))

        numbers = "96891\n82491\n86437"
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.FOURTH))

        numbers = "6891\n2491\n6437"
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.FIFTH))

        numbers = "891\n491\n437"
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.SIXTH))

        numbers = "055\n816\n292"
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.ADDITION_SIXTH))
    }

    @Test
    fun getSixthAndAdditionSixthList() {
        var numbers = listOf("891", "491", "437", "055", "816", "292")
        assertEquals(numbers, dto.getSixthAndAdditionSixthList())
    }

    @Test
    fun getSixthAndAdditionSixthString() {
        var numbers = "891, 491, 437, 055, 816, 292"
        assertEquals(numbers, dto.getSixthAndAdditionSixthString())
    }

    @Test
    fun getSuperDataObject() {
        val dataObj = dto.getDataObject(SInvAppPrizeNumListDTO.PrizeType.SUPER)
        assertEquals("特別獎", dataObj.title)
        assertEquals("75350343", dataObj.number[0])
        assertEquals("10,000,000", dataObj.value)
    }

    @Test
    fun getSpecialDataObject() {
        val dataObj = dto.getDataObject(SInvAppPrizeNumListDTO.PrizeType.SPECIAL)
        assertEquals("特獎", dataObj.title)
        assertEquals("67035249", dataObj.number[0])
        assertEquals("2,000,000", dataObj.value)
    }

    @Test
    fun getFirstDataObject() {
        val dataObj = dto.getDataObject(SInvAppPrizeNumListDTO.PrizeType.FIRST)
        assertEquals("頭獎", dataObj.title)
        assertEquals("03696891", dataObj.number[0])
        assertEquals("79882491", dataObj.number[1])
        assertEquals("77486437", dataObj.number[2])
        assertEquals("200,000", dataObj.value)
    }

    @Test
    fun getSecondDataObject() {
        val dataObj = dto.getDataObject(SInvAppPrizeNumListDTO.PrizeType.SECOND)
        assertEquals("二獎", dataObj.title)
        assertEquals("與頭獎中獎號碼末7位相同", dataObj.number[0])
        assertEquals("40,000", dataObj.value)
    }

    @Test
    fun getThirdDataObject() {
        val dataObj = dto.getDataObject(SInvAppPrizeNumListDTO.PrizeType.THIRD)
        assertEquals("三獎", dataObj.title)
        assertEquals("與頭獎中獎號碼末6位相同", dataObj.number[0])
        assertEquals("10,000", dataObj.value)
    }

    @Test
    fun getFourthDataObject() {
        val dataObj = dto.getDataObject(SInvAppPrizeNumListDTO.PrizeType.FOURTH)
        assertEquals("四獎", dataObj.title)
        assertEquals("與頭獎中獎號碼末5位相同", dataObj.number[0])
        assertEquals("4,000", dataObj.value)
    }

    @Test
    fun getFifthDataObject() {
        val dataObj = dto.getDataObject(SInvAppPrizeNumListDTO.PrizeType.FIFTH)
        assertEquals("五獎", dataObj.title)
        assertEquals("與頭獎中獎號碼末4位相同", dataObj.number[0])
        assertEquals("1,000", dataObj.value)
    }

    @Test
    fun getSixthDataObject() {
        val dataObj = dto.getDataObject(SInvAppPrizeNumListDTO.PrizeType.SIXTH)
        assertEquals("六獎", dataObj.title)
        assertEquals("與頭獎中獎號碼末3位相同", dataObj.number[0])
        assertEquals("200", dataObj.value)
    }

    @Test
    fun getAdditionSixthDataObject() {
        val dataObj = dto.getDataObject(SInvAppPrizeNumListDTO.PrizeType.ADDITION_SIXTH)
        assertEquals("增開六獎", dataObj.title)
        assertEquals("055", dataObj.number[0])
        assertEquals("816", dataObj.number[1])
        assertEquals("292", dataObj.number[2])
        assertEquals("200", dataObj.value)
    }
}