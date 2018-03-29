package com.tonynowater.uniforminvoicehelper.data.net.api.util

import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceDetailDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceHeaderDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SCarrierInvoiceDetailEntity
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SCarrierInvoiceHeaderEntity
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SInvAppPrizeNumListEntity
import com.tonynowater.uniforminvoicehelper.util.STimeUtil

/**
 * Created by tonyliao on 2018/3/28.
 */
class STransferEntityToDtoUtil {
    fun transferInvPrizeNumEntityToDTO(entity: SInvAppPrizeNumListEntity): SInvAppPrizeNumListDTO {
        return SInvAppPrizeNumListDTO(
                prizeAmtList = transferToNonEmptyList(listOf(entity.superPrizeAmt
                        , entity.spcPrizeAmt
                        , entity.firstPrizeAmt
                        , entity.secondPrizeAmt
                        , entity.thirdPrizeAmt
                        , entity.fourthPrizeAmt
                        , entity.fifthPrizeAmt
                        , entity.sixthPrizeAmt))
                , superPrizeNo = entity.superPrizeNo
                , spcPrizeNo = transferToNonEmptyList(listOf(entity.spcPrizeNo, entity.spcPrizeNo2, entity.spcPrizeNo3))
                , firstPrizeNo = transferToNonEmptyList(listOf(entity.firstPrizeNo1
                    , entity.firstPrizeNo2
                    , entity.firstPrizeNo3
                    , entity.firstPrizeNo4
                    , entity.firstPrizeNo5
                    , entity.firstPrizeNo6
                    , entity.firstPrizeNo7
                    , entity.firstPrizeNo8
                    , entity.firstPrizeNo9
                    , entity.firstPrizeNo10))
                , sixPrizeNo = transferToNonEmptyList(listOf(entity.sixthPrizeNo1
                    , entity.sixthPrizeNo2
                    , entity.sixthPrizeNo3
                    , entity.sixthPrizeNo4
                    , entity.sixthPrizeNo5
                    , entity.sixthPrizeNo6))
        )
    }

    fun transferCarrierHeaderEntityToDTO(entity: SCarrierInvoiceHeaderEntity): MutableList<SCarrierInvoiceHeaderDTO> {
        val list = mutableListOf<SCarrierInvoiceHeaderDTO>()
        entity.details.forEachIndexed({ index, entity ->
            val month = entity.invDate.month
            val date = entity.invDate.date
            list.add(SCarrierInvoiceHeaderDTO(
                    date = "$month/$date(${STimeUtil.transferWeekDays(entity.invDate.day)})"
                    , formatCEDate = STimeUtil.transferTaiwanYearToCommonEra(entity.invDate.year, month, date)
                    , invoiceNo = entity.invNum
                    , sellerName = entity.sellerName
                    , amount = entity.amount.toString()))
        })
        return list
    }

    fun transferCarrierDetailEntityToDTO(entity: SCarrierInvoiceDetailEntity): MutableList<SCarrierInvoiceDetailDTO> {
        val list = mutableListOf<SCarrierInvoiceDetailDTO>()
        entity.details.forEachIndexed({ index, entity ->
            list.add(SCarrierInvoiceDetailDTO(
                    rowNum = entity.rowNum.toInt()
                    , description = entity.description
                    , quantity = entity.quantity
                    , unitPrice = entity.unitPrice
                    , amount = entity.amount))
        })
        return list
    }

    /**
     * 去除空值的欄位
     */
    private fun transferToNonEmptyList(originList: List<String?>): List<String> {
        val nonEmptyList = mutableListOf<String>()
        originList.forEach {
            if (it != null && it.isNotEmpty()) {
                nonEmptyList.add(it!!)
            }
        }
        return nonEmptyList
    }
}