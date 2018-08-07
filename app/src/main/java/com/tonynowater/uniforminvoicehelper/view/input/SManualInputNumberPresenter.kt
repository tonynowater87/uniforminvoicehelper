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

    private var sixthNumberList: List<String>? = null
    private var specialNumberList: List<String>? = null

    fun clickClearButton() {
        mView?.removeTextWatcher()
        mView?.setInput("")
        mView?.addTextWatcher()
    }

    val callbackQueryPrizeList: IOnNetQueryCallback<SInvAppPrizeNumListDTO> = object : IOnNetQueryCallback<SInvAppPrizeNumListDTO> {
        override fun onSuccess(entity: SInvAppPrizeNumListDTO) {
            sixthNumberList = entity.getSixthAndAdditionSixthList()
            specialNumberList = entity.getSpecialPrizeNumberList()
            mView?.showData(entity)
            mView?.addTextWatcher()
        }

        override fun onFailure(throwable: Throwable) {
            mView?.showToast(throwable.message!!)
        }
    }

    fun queryPrizeList() {
        mModule.getPrizeNumberList(callbackNet = callbackQueryPrizeList)
    }

    fun showDefaultWinningDetail() {
        mView?.showWinningDetail(getWinningObj().getWinningDetail())
    }

    fun afterTextChanged(sBefore: String, sAfter: String) {
        when {
            sBefore.length + 1 < 3 -> mView?.setInput(sBefore + sAfter)
            sBefore.length + 1 == 3 -> mView?.let {
                val winningObj = getWinningObj()
                when {
                    isWinningSpecial(sBefore + sAfter) -> {
                        it.setInput("有機會中特獎:$sBefore$sAfter")
                        winningObj.winning()
                    }
                    isWinningSixth(sBefore + sAfter) -> {
                        it.setInput("中六獎:$sBefore$sAfter")
                        winningObj.winning()
                    }
                    else -> {
                        winningObj.notWinning()
                        it.setInput("沒中獎:$sBefore$sAfter")
                    }
                }
                it.showWinningDetail(winningObj.getWinningDetail())
            }
            sBefore.length + 1 > 3 -> mView?.let {
                it.setInput(sAfter)
            }
        }
    }

    private fun getWinningObj() = SSharePrefUtil.getObjFromJson(SP_KEY_WINNING_OBJ_JSON, InputWinningObj::class.java)

    private fun isWinningSpecial(input: String): Boolean {
        specialNumberList?.let {
            for (number in it) {
                if (number == input) return true
            }
        }

        return false
    }

    private fun isWinningSixth(input: String): Boolean {
        sixthNumberList?.let {
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
