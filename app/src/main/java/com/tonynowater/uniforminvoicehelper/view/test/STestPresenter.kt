package com.tonynowater.uniforminvoicehelper.view.test

import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceHeaderDetailDTO
import com.tonynowater.uniforminvoicehelper.base.*
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestPresenter @Inject constructor() : SBasePresenter<STestPresenter.ITestView, SBaseMvpModule>(), SBaseRecyclerViewAdapter.OnClickItemListener<SCarrierInvoiceHeaderDetailDTO> {

    fun login(cardNo: String, cardEncrypt: String) {
        mView?.showLoading()
        mModule.getCarrierInvoiceHeader(cardNo, cardEncrypt, object : IOnQueryListener<MutableList<SCarrierInvoiceHeaderDetailDTO>> {
            override fun onSuccess(entity: MutableList<SCarrierInvoiceHeaderDetailDTO>) {
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

    override fun onClickItem(position: Int, dto: SCarrierInvoiceHeaderDetailDTO) {
        mModule.getCarrierInvoiceDetail(dto.invoiceNo, dto.formatDate, dto.sellerName, dto.amount)
    }

    interface ITestView : IBaseMvpView {
        fun notifyData(entity: MutableList<SCarrierInvoiceHeaderDetailDTO>)
    }
}