package com.tonynowater.uniforminvoicehelper.view

import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceHeaderDetailDTO
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
        mModule.getCarrierInvoiceHeader(cardNo, cardEncrypt, object : IOnQueryListener<List<SCarrierInvoiceHeaderDetailDTO>> {
            override fun onSuccess(entity: List<SCarrierInvoiceHeaderDetailDTO>) {
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