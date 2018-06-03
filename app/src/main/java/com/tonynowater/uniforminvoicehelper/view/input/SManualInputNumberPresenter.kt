package com.tonynowater.uniforminvoicehelper.view.input

import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.data.net.IOnNetQueryCallback
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_WINNING_OBJ_JSON
import com.tonynowater.uniforminvoicehelper.util.sp.SSharePrefUtil
import javax.inject.Inject

class SManualInputNumberPresenter @Inject constructor(module: SNetRepository) : SBasePresenter<SManualInputNumberPresenter.IManualInputNumberView, SNetRepository>(module) {

    private var numberList: List<String>? = null

    fun clickClearButton() {
        mView?.removeTextWatcher()
        mView?.setInput("")
        mView?.addTextWatcher()
    }

    val callbackQueryPrizeList: IOnNetQueryCallback<SInvAppPrizeNumListDTO> = object : IOnNetQueryCallback<SInvAppPrizeNumListDTO> {
        override fun onSuccess(entity: SInvAppPrizeNumListDTO) {
            numberList = entity.getSixthAndAdditionSixthList()
            mView?.showData(entity)
            mView?.addTextWatcher()
        }

        override fun onFailure(throwable: Throwable) {
            mView?.showToast(throwable.message!!)
        }
    }

    fun queryPrizeList() {
        mModule.getPrizeNumberList(callbackQueryPrizeList)
    }

    fun afterTextChanged(sBefore: String, sAfter: String) {
        when {
            sBefore.length + 1 < 3 -> mView?.setInput(sBefore + sAfter)
            sBefore.length + 1 == 3 -> mView?.let {
                val winningObj = SSharePrefUtil.getObjFromJson(SP_KEY_WINNING_OBJ_JSON, InputWinningObj::class.java)
                if (isWinning(sBefore + sAfter)) {
                    it.setInput("中獎:$sBefore$sAfter")
                    winningObj.winning()
                } else {
                    winningObj.notWinning()
                    it.setInput("沒中:$sBefore$sAfter")
                }
                it.showWinningDetail(winningObj.getWinningDetail())
            }
            sBefore.length + 1 > 3 -> mView?.let {
                it.setInput(sAfter)
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
        fun addTextWatcher()
        fun removeTextWatcher()
        fun showData(dto: SInvAppPrizeNumListDTO)
        fun setInput(input: String)
        fun showWinningDetail(detail: String)
    }
}
