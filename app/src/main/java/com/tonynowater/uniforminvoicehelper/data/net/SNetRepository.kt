package com.tonynowater.uniforminvoicehelper.data.net

import com.tonynowater.uniforminvoicehelper.data.net.api.ICarrierApi
import com.tonynowater.uniforminvoicehelper.data.net.api.IInvAppApi
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceDetailDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceHeaderDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.util.STransferEntityToDtoUtil
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_ACCOUNT
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_PASSWORD
import com.tonynowater.uniforminvoicehelper.util.sp.SSharePrefUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class SNetRepository @Inject constructor(var invAppClient: IInvAppApi
                                         , var carrierClient: ICarrierApi) {

    @Inject
    lateinit var mTransferEntityToDtoUtil: STransferEntityToDtoUtil

    fun getPrizeNumberList(callbackNet: IOnNetQueryCallback<SInvAppPrizeNumListDTO>) {
        invAppClient.getPrizeNumberList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.is200()) {
                        callbackNet.onSuccess(mTransferEntityToDtoUtil.transferInvPrizeNumEntityToDTO(it))
                    } else {
                        callbackNet.onFailure(Throwable(it.msgCode()))
                    }
                }, {
                    callbackNet.onFailure(it)
                })
    }

    fun getCarrierInvoiceDetail(invNum: String, invDate: String, callbackNet: IOnNetQueryCallback<MutableList<SCarrierInvoiceDetailDTO>>) {
        carrierClient.getCarrierInvoiceDetail(invNum = invNum, invDate = invDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.is200()) {
                        callbackNet.onSuccess(mTransferEntityToDtoUtil.transferCarrierDetailEntityToDTO(it))
                    } else {
                        callbackNet.onFailure(Throwable(it.msgCode()))
                    }
                }, {
                    callbackNet.onFailure(it)
                })
    }

    fun getCarrierInvoiceHeader(startDate: String, endDate: String, onlyWinningInv: Boolean, callbackNet: IOnNetQueryCallback<MutableList<SCarrierInvoiceHeaderDTO>>) {
        carrierClient.getCarrierInvoiceHeader(startDate = startDate, endDate = endDate, onlyWinningInv = if (onlyWinningInv) "Y" else "N")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.is200()) {
                        callbackNet.onSuccess(mTransferEntityToDtoUtil.transferCarrierHeaderEntityToDTO(it))
                    } else {
                        callbackNet.onFailure(Throwable(it.msgCode()))
                    }
                }, {
                    callbackNet.onFailure(it)
                })
    }

    fun login(cardNo: String, cardEncrypt: String, callbackNet: IOnNetQueryCallback<Any>) {
        carrierClient.getCarrierInvoiceHeader(cardNo = cardNo, cardEncrypt = cardEncrypt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.is200()) {
                        SSharePrefUtil.putString(SP_KEY_ACCOUNT, cardNo)
                        SSharePrefUtil.putString(SP_KEY_PASSWORD, cardEncrypt)
                        callbackNet.onSuccess(true)
                    } else {
                        callbackNet.onFailure(Throwable(it.msgCode()))
                    }
                }, {
                    callbackNet.onFailure(it)
                })
    }
}