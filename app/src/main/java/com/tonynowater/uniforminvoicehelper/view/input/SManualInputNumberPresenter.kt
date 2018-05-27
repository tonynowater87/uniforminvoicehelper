package com.tonynowater.uniforminvoicehelper.view.input

import android.widget.EditText
import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import javax.inject.Inject

class SManualInputNumberPresenter @Inject constructor(module: SNetRepository) : SBasePresenter<SManualInputNumberPresenter.IManualInputNumberView, SNetRepository>(module) {
    fun afterTextChanged(sBefore: String, sAfter: String) {
        when {
            sBefore.length + 1 < 3 -> mView?.let {
                it.getInputView().setText(sBefore + sAfter)
            }
            sBefore.length + 1 == 3 -> mView?.let {
                it.getInputView().setText("中了:$sBefore$sAfter")
            }
            sBefore.length + 1 > 3 -> mView?.let {
                it.getInputView().setText(sAfter)
            }
        }
    }

    interface IManualInputNumberView : IBaseView {
        fun getInputView(): EditText
    }
}
