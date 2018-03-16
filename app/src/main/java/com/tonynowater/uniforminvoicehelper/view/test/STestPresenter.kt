package com.tonynowater.uniforminvoicehelper.view.test

import com.tonynowater.uniforminvoicehelper.api.dto.SInvAppPrizeNumListDTO
import com.tonynowater.uniforminvoicehelper.base.*
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestPresenter @Inject constructor() : SBasePresenter<STestPresenter.ITestView, SBaseModule>(), SBaseRecyclerViewAdapter.OnClickItemListener<String> {

    fun clickTestButton() {
        mView?.showLoading()
        mModule.getPrizeNumberList(object : IOnQueryListener<SInvAppPrizeNumListDTO> {
            override fun onSuccess(entity: SInvAppPrizeNumListDTO) {
                mView?.hideLoading()
                mView?.notifyData(entity.sixPrizeNo)
            }

            override fun onFailure(throwable: Throwable) {
                mView?.hideLoading()
                mView?.showToast(throwable.message)
            }
        })
    }

    override fun onClickItem(position: Int, item: String) {
        mView?.showToast(item)
    }

    interface ITestView : IBaseView {
        fun notifyData(entity: List<String>)
    }
}