package com.tonynowater.uniforminvoicehelper.view.test

import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter
import com.tonynowater.uniforminvoicehelper.data.net.IOnNetQueryCallback
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceDetailDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceHeaderDTO
import com.tonynowater.uniforminvoicehelper.util.SBarCodeImageGenerator
import com.tonynowater.uniforminvoicehelper.view.component.SBarCodeView
import javax.inject.Inject


/**
 * Created by tonyliao on 2018/3/15.
 */
class STestPresenter @Inject constructor() : SBasePresenter<STestPresenter.ITestView, SNetRepository>(), SBaseRecyclerViewAdapter.OnClickItemListener<String> {

    fun clickTestButton(account: String, password: String) {
        mView?.showLoading()
        mModule.getCarrierInvoiceHeader(account, password, object : IOnNetQueryCallback<MutableList<SCarrierInvoiceHeaderDTO>> {
            override fun onSuccess(entity: MutableList<SCarrierInvoiceHeaderDTO>) {
                mModule.getCarrierInvoiceDetail(entity[0].invoiceNo, entity[0].formatCEDate, object : IOnNetQueryCallback<MutableList<SCarrierInvoiceDetailDTO>> {
                    override fun onFailure(throwable: Throwable) {
                        mView?.hideLoading()
                    }

                    override fun onSuccess(entity: MutableList<SCarrierInvoiceDetailDTO>) {
                        mView?.hideLoading()
                    }
                })
            }

            override fun onFailure(throwable: Throwable) {
                mView?.hideLoading()
            }
        })
    }

    override fun onClickItem(position: Int, item: String) {
        mView?.showToast(item)
    }

    interface ITestView : IBaseView {
        fun showBarCode(barCodeItem: SBarCodeImageGenerator.BarCodeItem)
        fun getBarcodeView():SBarCodeView
    }
}