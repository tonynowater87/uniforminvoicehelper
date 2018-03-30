package com.tonynowater.uniforminvoicehelper.data.net.api.dto

import com.tonynowater.uniforminvoicehelper.data.net.api.SBaseTestJsonData
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SInvAppPrizeNumListEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by tonyliao on 2018/3/30.
 */
class SInvAppPrizeNumListDTOTest : SBaseTestJsonData() {

    lateinit var dto: SInvAppPrizeNumListDTO

    @Before
    fun setUp() {
        val entity = gson.fromJson(json_prize_number_list_200, SInvAppPrizeNumListEntity::class.java)
        dto = transferUtil.transferInvPrizeNumEntityToDTO(entity)
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
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.FORTH))

        numbers = "6891\n2491\n6437"
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.FIFTH))

        numbers = "891\n491\n437"
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.SIXTH))

        numbers = "055\n816\n292"
        assertEquals(numbers, dto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.ADDITION_SIXTH))
    }

    @Test
    fun getSixthAndAdditionSixth() {
        var numbers = "891\n491\n437\n055\n816\n292"
        assertEquals(numbers, dto.getSixthAndAdditionSixth())
    }
}