package com.tonynowater.uniforminvoicehelper.view.test

import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceDetailDTO
import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceHeaderDTO
import com.tonynowater.uniforminvoicehelper.base.*
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestPresenter @Inject constructor() : SBasePresenter<STestPresenter.ITestView, SBaseModule>(), SBaseRecyclerViewAdapter.OnClickItemListener<SCarrierInvoiceHeaderDTO> {

    fun login(cardNo: String, cardEncrypt: String) {
        mView?.showLoading()
        mModule.getCarrierInvoiceHeader(cardNo, cardEncrypt, object : IOnQueryListener<MutableList<SCarrierInvoiceHeaderDTO>> {
            override fun onSuccess(entity: MutableList<SCarrierInvoiceHeaderDTO>) {
                mView?.hideLoading()
                mView?.onSuccess()
                mView?.notifyData(entity)
            }

            override fun onFailure(throwable: Throwable) {
                mView?.hideLoading()
                mView?.onError(throwable.message!!)
            }
        })
    }

    override fun onClickItem(position: Int, dto: SCarrierInvoiceHeaderDTO) {
        mView?.showLoading()
        mModule.getCarrierInvoiceDetail(dto.invoiceNo, dto.formatDate, object : IOnQueryListener<MutableList<SCarrierInvoiceDetailDTO>> {
            override fun onSuccess(entity: MutableList<SCarrierInvoiceDetailDTO>) {
                mView?.hideLoading()
            }

            override fun onFailure(throwable: Throwable) {
                mView?.hideLoading()
            }
        })
    }

    interface ITestView : IBaseView {
        fun notifyData(entity: MutableList<SCarrierInvoiceHeaderDTO>)
    }
}