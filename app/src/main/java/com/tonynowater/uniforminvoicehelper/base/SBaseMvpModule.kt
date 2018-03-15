package com.tonynowater.uniforminvoicehelper.base

import com.tonynowater.uniforminvoicehelper.api.INetApi
import com.tonynowater.uniforminvoicehelper.api.dto.SRiderInvoiceHeaderDetailDTO
import com.tonynowater.uniforminvoicehelper.api.entity.SRiderInvoiceHeaderEntity
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

    fun getRiderInvoiceHeader(cardNo: String, cardEncrypt: String, listener: IOnQueryListener<MutableList<SRiderInvoiceHeaderDetailDTO>>) {
        netClient.getRiderInvoiceHeader(cardNo = cardNo, cardEncrypt = cardEncrypt)
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

    private fun transferEntityToDTO(it: SRiderInvoiceHeaderEntity): MutableList<SRiderInvoiceHeaderDetailDTO> {
        val list = mutableListOf<SRiderInvoiceHeaderDetailDTO>()
        it.details.forEachIndexed({ index, entity ->
            list.add(SRiderInvoiceHeaderDetailDTO(
                    "${entity.invDate.month}/${entity.invDate.date}(${STimeUtil.transferWeekDays(entity.invDate.day)})"
                    , entity.invNum
                    , entity.sellerName))
        })
        return list
    }
}