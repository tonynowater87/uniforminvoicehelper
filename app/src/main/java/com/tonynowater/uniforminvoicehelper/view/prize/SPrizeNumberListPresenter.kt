package com.tonynowater.uniforminvoicehelper.view.prize

import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.data.net.IOnNetQueryCallback
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/28.
 */
class SPrizeNumberListPresenter @Inject constructor(module: SNetRepository) : SBasePresenter<IBaseView, SNetRepository>(module) {

    val callback: IOnNetQueryCallback<SInvAppPrizeNumListDTO> = object : IOnNetQueryCallback<SInvAppPrizeNumListDTO> {
        override fun onSuccess(entity: SInvAppPrizeNumListDTO) {
            mView?.hideLoading()
            mView?.showToast(entity.superPrizeNo)
        }

        override fun onFailure(throwable: Throwable) {
            mView?.hideLoading()
            mView?.showToast(throwable.message!!)
        }
    }

    fun getPrizeNumberList() {
        mView?.showLoading()
        mModule.getPrizeNumberList(callback)
    }
}