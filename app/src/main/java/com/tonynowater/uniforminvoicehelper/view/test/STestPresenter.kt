package com.tonynowater.uniforminvoicehelper.view.test

import android.widget.Toast
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.api.dto.SRiderInvoiceHeaderDetailDTO
import com.tonynowater.uniforminvoicehelper.base.*
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestPresenter @Inject constructor() : SBasePresenter<STestPresenter.ITestView, SBaseMvpModule>(), SBaseRecyclerViewAdapter.OnClickItemListener<SRiderInvoiceHeaderDetailDTO> {

    fun login(cardNo: String, cardEncrypt: String) {
        mView?.showLoading()
        mModule.getRiderInvoiceHeader(cardNo, cardEncrypt, object : IOnQueryListener<MutableList<SRiderInvoiceHeaderDetailDTO>> {
            override fun onSuccess(entity: MutableList<SRiderInvoiceHeaderDetailDTO>) {
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

    override fun onClickItem(position: Int, t: SRiderInvoiceHeaderDetailDTO) {
        Toast.makeText(SApplication.context, t.invoiceNo, Toast.LENGTH_SHORT).show()
    }

    interface ITestView : IBaseMvpView {
        fun notifyData(entity: MutableList<SRiderInvoiceHeaderDetailDTO>)
    }
}