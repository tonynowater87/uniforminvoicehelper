package com.tonynowater.uniforminvoicehelper.view.query

import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.data.net.IOnNetQueryCallback
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceDetailDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceHeaderDTO
import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/4/10.
 */
class SCarrierQueryListPresenter @Inject constructor(module: SNetRepository) : SBasePresenter<SCarrierQueryListPresenter.ICarrierQueryListView, SNetRepository>(module) {

    private val callbackHeader = object : IOnNetQueryCallback<MutableList<SCarrierInvoiceHeaderDTO>> {
        override fun onSuccess(entity: MutableList<SCarrierInvoiceHeaderDTO>) {
            mView?.hideLoading()
            mView?.showHeader(entity)
        }

        override fun onFailure(throwable: Throwable) {
            mView?.hideLoading()
            mView?.showError(throwable.message!!)
        }
    }

    fun queryHeader(eCarrierQueryType: ECarrierQueryType) {

        when (eCarrierQueryType) {
            ECarrierQueryType.THIS_MONTH -> {
                query(eCarrierQueryType.getDateItem())
            }
            ECarrierQueryType.LAST_MONTH -> {
                query(eCarrierQueryType.getDateItem())
            }
            ECarrierQueryType.CUSTOM_MONTH -> {

            }
            ECarrierQueryType.PRIZE_RECORD -> {

            }
        }
    }

    private fun query(dateItem: STimeUtil.DateItem?) {
        dateItem?.let {
            mView?.showLoading()
            mModule.getCarrierInvoiceHeader(dateItem.startDate, dateItem.endDate, callbackHeader)
        }
    }

    private val callbackDetail = object : IOnNetQueryCallback<MutableList<SCarrierInvoiceDetailDTO>> {
        override fun onSuccess(entity: MutableList<SCarrierInvoiceDetailDTO>) {
            mView?.hideLoading()
            mView?.showDetail(entity)
        }

        override fun onFailure(throwable: Throwable) {
            mView?.hideLoading()
            mView?.showError(throwable.message!!)
        }
    }

    fun queryDetail(dto: SCarrierInvoiceHeaderDTO) {
        mView?.showLoading()
        mModule.getCarrierInvoiceDetail(dto.invoiceNo, dto.formatCEDate, callbackDetail)
    }

    interface ICarrierQueryListView : IBaseView {
        fun showHeader(dto: List<SCarrierInvoiceHeaderDTO>)
        fun showDetail(dto: List<SCarrierInvoiceDetailDTO>)
    }
}