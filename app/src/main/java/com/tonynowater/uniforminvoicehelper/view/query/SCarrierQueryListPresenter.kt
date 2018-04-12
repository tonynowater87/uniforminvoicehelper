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

    private var mQuantity: Int = 0
    private var mSum: Int = 0
    private lateinit var mDateItem:STimeUtil.DateItem

    private val callbackHeader = object : IOnNetQueryCallback<MutableList<SCarrierInvoiceHeaderDTO>> {
        override fun onSuccess(entity: MutableList<SCarrierInvoiceHeaderDTO>) {
            mView?.hideLoading()
            calculateQuantity(entity)
            mView?.showDate(mDateItem)
            mView?.showQuantity(mQuantity, mSum)
            mView?.showHeader(entity)
        }

        private fun calculateQuantity(entity: MutableList<SCarrierInvoiceHeaderDTO>) {
            entity.forEach {
                mSum += it.amount.toInt()
            }

            mQuantity = entity.size
        }

        override fun onFailure(throwable: Throwable) {
            mView?.hideLoading()
            mView?.showError(throwable.message!!)
        }
    }

    fun queryHeader(dateItem: STimeUtil.DateItem?) {
        if (dateItem != null) {
            mDateItem = dateItem
            mSum = 0
            mView?.showLoading()
            mModule.getCarrierInvoiceHeader(dateItem.startDate, dateItem.endDate, callbackHeader)
        } else {
            mView?.showDateLimitPickerDialog()
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
        fun showDate(dateItem: STimeUtil.DateItem?)
        fun showQuantity(quantity: Int, sum: Int)
        fun showDateLimitPickerDialog()
        fun showHeader(dto: List<SCarrierInvoiceHeaderDTO>)
        fun showDetail(dto: List<SCarrierInvoiceDetailDTO>)
    }
}