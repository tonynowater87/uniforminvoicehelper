package com.tonynowater.uniforminvoicehelper.view

import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceHeaderDTO
import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.IOnQueryListener
import com.tonynowater.uniforminvoicehelper.base.SBaseModule
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class SLoginPresenter @Inject constructor() : SBasePresenter<IBaseView, SBaseModule>() {

    fun login(cardNo: String, cardEncrypt: String) {
        mView?.showLoading()
        mModule.getCarrierInvoiceHeader(cardNo, cardEncrypt, object : IOnQueryListener<List<SCarrierInvoiceHeaderDTO>> {
            override fun onSuccess(entity: List<SCarrierInvoiceHeaderDTO>) {
                mView?.hideLoading()
                mView?.onSuccess()
            }

            override fun onFailure(throwable: Throwable) {
                mView?.hideLoading()
                mView?.onError(throwable.message!!)
            }
        })
    }
}