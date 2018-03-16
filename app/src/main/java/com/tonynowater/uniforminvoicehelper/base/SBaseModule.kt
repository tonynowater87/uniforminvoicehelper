package com.tonynowater.uniforminvoicehelper.base

import com.tonynowater.uniforminvoicehelper.api.ICarrierApi
import com.tonynowater.uniforminvoicehelper.api.IInvAppApi
import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceDetailDTO
import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceHeaderDTO
import com.tonynowater.uniforminvoicehelper.api.dto.SInvAppPrizeNumListDTO
import com.tonynowater.uniforminvoicehelper.api.entity.SCarrierInvoiceDetailEntity
import com.tonynowater.uniforminvoicehelper.api.entity.SCarrierInvoiceHeaderEntity
import com.tonynowater.uniforminvoicehelper.api.entity.SInvAppPrizeNumListEntity
import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_ACCOUNT
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_PASSWORD
import com.tonynowater.uniforminvoicehelper.util.sp.SSharePrefUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class SBaseModule @Inject constructor() {

    @Inject
    lateinit var invAppClient: IInvAppApi

    @Inject
    lateinit var carrierClient: ICarrierApi

    fun getPrizeNumberList(listener: IOnQueryListener<SInvAppPrizeNumListDTO>) {
        invAppClient.getPrizeNumberList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.is200()) {
                        listener.onSuccess(transferInvPrizeNumEntityToDTO(it))
                    } else {
                        listener.onFailure(Throwable(it.msgCode()))
                    }
                }, {
                    listener.onFailure(it)
                })
    }

    fun getCarrierInvoiceDetail(invNum: String, invDate: String, listener: IOnQueryListener<MutableList<SCarrierInvoiceDetailDTO>>) {
        carrierClient.getCarrierInvoiceDetail(invNum = invNum, invDate = invDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.is200()) {
                        listener.onSuccess(transferCarrierDetailEntityToDTO(it))
                    } else {
                        listener.onFailure(Throwable(it.msgCode()))
                    }
                }, {
                    listener.onFailure(it)
                })
    }

    fun getCarrierInvoiceHeader(cardNo: String, cardEncrypt: String, listener: IOnQueryListener<MutableList<SCarrierInvoiceHeaderDTO>>) {
        carrierClient.getCarrierInvoiceHeader(cardNo = cardNo, cardEncrypt = cardEncrypt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.is200()) {
                        SSharePrefUtil.putString(SP_KEY_ACCOUNT, cardNo)
                        SSharePrefUtil.putString(SP_KEY_PASSWORD, cardEncrypt)
                        listener.onSuccess(transferCarrierHeaderEntityToDTO(it))
                    } else {
                        listener.onFailure(Throwable(it.msgCode()))
                    }
                }, {
                    listener.onFailure(it)
                })
    }

    private fun transferCarrierHeaderEntityToDTO(entity: SCarrierInvoiceHeaderEntity): MutableList<SCarrierInvoiceHeaderDTO> {
        val list = mutableListOf<SCarrierInvoiceHeaderDTO>()
        entity.details.forEachIndexed({ index, entity ->
            val month = entity.invDate.month
            val date = entity.invDate.date
            list.add(SCarrierInvoiceHeaderDTO(
                    date = "$month/$date(${STimeUtil.transferWeekDays(entity.invDate.day)})"
                    , formatDate = STimeUtil.transferTaiwanYearToCommonEra(entity.invDate.year, month, date)
                    , invoiceNo = entity.invNum
                    , sellerName = entity.sellerName
                    , amount = entity.amount.toString()))
        })
        return list
    }

    private fun transferCarrierDetailEntityToDTO(entity: SCarrierInvoiceDetailEntity): MutableList<SCarrierInvoiceDetailDTO> {
        val list = mutableListOf<SCarrierInvoiceDetailDTO>()
        entity.details.forEachIndexed({ index, entity ->
            list.add(SCarrierInvoiceDetailDTO(
                    rowNum = entity.rowNum
                    , description = entity.description
                    , quantity = entity.quantity
                    , unitPrice = entity.unitPrice
                    , amount = entity.amount))
        })
        return list
    }

    private fun transferInvPrizeNumEntityToDTO(entity: SInvAppPrizeNumListEntity): SInvAppPrizeNumListDTO {
        return SInvAppPrizeNumListDTO(
                prizeAmtList = listOf(entity.superPrizeAmt
                        , entity.spcPrizeAmt
                        , entity.firstPrizeAmt
                        , entity.secondPrizeAmt
                        , entity.thirdPrizeAmt
                        , entity.fourthPrizeAmt
                        , entity.fifthPrizeAmt
                        , entity.sixthPrizeAmt)
                , superPrizeNo = entity.superPrizeNo
                , spcPrizeNo = listOf(entity.spcPrizeNo, entity.spcPrizeNo2, entity.spcPrizeNo3)
                , firstPrizeNo = listOf(entity.firstPrizeNo1
                    , entity.firstPrizeNo2
                    , entity.firstPrizeNo3
                    , entity.firstPrizeNo4
                    , entity.firstPrizeNo5
                    , entity.firstPrizeNo6
                    , entity.firstPrizeNo7
                    , entity.firstPrizeNo8
                    , entity.firstPrizeNo9
                    , entity.firstPrizeNo10)
                , sixPrizeNo = listOf(entity.sixthPrizeNo1
                    , entity.sixthPrizeNo2
                    , entity.sixthPrizeNo3
                    , entity.sixthPrizeNo4
                    , entity.sixthPrizeNo5
                    , entity.sixthPrizeNo6)
        )
    }
}