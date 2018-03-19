package com.tonynowater.uniforminvoicehelper.view.test

import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import com.tonynowater.uniforminvoicehelper.base.*
import com.tonynowater.uniforminvoicehelper.data.net.IOnNetQueryCallback
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepositoy
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestPresenter @Inject constructor() : SBasePresenter<STestPresenter.ITestView, SNetRepositoy>(), SBaseRecyclerViewAdapter.OnClickItemListener<String> {

    fun clickTestButton() {
        mView?.showLoading()
        mModule.getPrizeNumberList(object : IOnNetQueryCallback<SInvAppPrizeNumListDTO> {
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