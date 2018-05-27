package com.tonynowater.uniforminvoicehelper.view.input

import android.widget.EditText
import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.data.net.IOnNetQueryCallback
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import javax.inject.Inject

class SManualInputNumberPresenter @Inject constructor(module: SNetRepository) : SBasePresenter<SManualInputNumberPresenter.IManualInputNumberView, SNetRepository>(module) {

    private var numberList: List<String>? = null

    fun queryPrizeList() {
        mModule.getPrizeNumberList(object : IOnNetQueryCallback<SInvAppPrizeNumListDTO> {
            override fun onSuccess(entity: SInvAppPrizeNumListDTO) {
                numberList = entity.getSixthAndAdditionSixthList()
                mView?.showData(entity)
            }

            override fun onFailure(throwable: Throwable) {
                mView?.showToast(throwable.message!!)
            }
        })
    }

    fun afterTextChanged(sBefore: String, sAfter: String) {
        when {
            sBefore.length + 1 < 3 -> mView?.let {
                it.getInputView().setText(sBefore + sAfter)
            }
            sBefore.length + 1 == 3 -> mView?.let {
                if (isWinning(sBefore + sAfter)) {
                    it.getInputView().setText("中獎:$sBefore$sAfter")
                } else {
                    it.getInputView().setText("沒中:$sBefore$sAfter")
                }
            }
            sBefore.length + 1 > 3 -> mView?.let {
                it.getInputView().setText(sAfter)
            }
        }
    }

    private fun isWinning(input: String): Boolean {
        numberList?.let {
            for (number in it) {
                if (number == input) return true
            }
        }
        return false
    }

    interface IManualInputNumberView : IBaseView {
        fun getInputView(): EditText
        fun showData(dto: SInvAppPrizeNumListDTO)
    }
}
