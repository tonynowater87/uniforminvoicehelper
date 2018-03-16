package com.tonynowater.uniforminvoicehelper.base

import com.tonynowater.uniforminvoicehelper.api.INetApi
import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceHeaderDetailDTO
import com.tonynowater.uniforminvoicehelper.api.entity.SCarrierInvoiceHeaderEntity
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
class SBaseMvpModule @Inject constructor() {

    @Inject
    lateinit var netClient: INetApi

    fun getCarrierInvoiceDetail(invNum: String, invDate: String, sellerName: String, amount: String) {
        netClient.getCarrierInvoiceDetail(invNum = invNum, invDate = invDate, sellerName = sellerName, amount = amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ println("success") }, { println("onError") })
    }

    fun getCarrierInvoiceHeader(cardNo: String, cardEncrypt: String, listener: IOnQueryListener<MutableList<SCarrierInvoiceHeaderDetailDTO>>) {
        netClient.getCarrierInvoiceHeader(cardNo = cardNo, cardEncrypt = cardEncrypt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.is200()) {
                        SSharePrefUtil.putString(SP_KEY_ACCOUNT, cardNo)
                        SSharePrefUtil.putString(SP_KEY_PASSWORD, cardEncrypt)
                        listener.onSuccess(transferEntityToDTO(it))
                    } else {
                        listener.onFailure(Throwable(it.msgCode()))
                    }
                }, {
                    listener.onFailure(it)
                })
    }

    private fun transferEntityToDTO(it: SCarrierInvoiceHeaderEntity): MutableList<SCarrierInvoiceHeaderDetailDTO> {
        val list = mutableListOf<SCarrierInvoiceHeaderDetailDTO>()
        it.details.forEachIndexed({ index, entity ->
            val month = entity.invDate.month
            val date = entity.invDate.date
            list.add(SCarrierInvoiceHeaderDetailDTO(
                    "$month/$date(${STimeUtil.transferWeekDays(entity.invDate.day)})"
                    , STimeUtil.fillDateZero(entity.invDate.year, month, date)
                    , entity.invNum
                    , entity.sellerName
                    , entity.amount.toString()))
        })
        return list
    }
}