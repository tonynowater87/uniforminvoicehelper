package com.tonynowater.uniforminvoicehelper.view.login

import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.data.net.IOnNetQueryCallback
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class SLoginPresenter @Inject constructor(mModule: SNetRepository) : SBasePresenter<IBaseView, SNetRepository>(mModule) {

    val loginCallback = object : IOnNetQueryCallback<Any> {
        override fun onSuccess(entity: Any) {
            mView?.hideLoading()
            mView?.showSuccess()
        }

        override fun onFailure(throwable: Throwable) {
            mView?.hideLoading()
            mView?.showError(throwable.message!!)
        }
    }

    fun login(cardNo: String, cardEncrypt: String) {
        mView?.showLoading()
        mModule.login(cardNo, cardEncrypt, loginCallback)
    }
}