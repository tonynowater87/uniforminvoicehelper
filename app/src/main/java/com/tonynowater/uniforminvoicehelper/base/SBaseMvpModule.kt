package com.tonynowater.uniforminvoicehelper.base

import com.tonynowater.uniforminvoicehelper.api.INetApi
import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceHeaderDetailDTO
import com.tonynowater.uniforminvoicehelper.api.entity.SCarrierInvoiceHeaderEntity
import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class SBaseMvpModule @Inject constructor() {

    @Inject
    lateinit var netClient: INetApi

    fun getCarrierInvoiceHeader(cardNo: String, cardEncrypt: String, listener: IOnQueryListener<MutableList<SCarrierInvoiceHeaderDetailDTO>>) {
        netClient.getCarrierInvoiceHeader(cardNo = cardNo, cardEncrypt = cardEncrypt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.is200()) {
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
            list.add(SCarrierInvoiceHeaderDetailDTO(
                    "${entity.invDate.month}/${entity.invDate.date}(${STimeUtil.transferWeekDays(entity.invDate.day)})"
                    , entity.invNum
                    , entity.sellerName))
        })
        return list
    }
}