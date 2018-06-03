package com.tonynowater.uniforminvoicehelper.view.input

import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_WINNING_OBJ_JSON
import com.tonynowater.uniforminvoicehelper.util.sp.SSharePrefUtil

/**
 * Created by tonyliao on 2018/6/3.
 */
data class InputWinningObj(var total: Int = 0, var winning: Int = 0) {
    fun winning() {
        total++
        winning++
        saveToSharedPreference()
    }

    fun notWinning() {
        total++
        saveToSharedPreference()
    }

    private fun saveToSharedPreference() {
        SSharePrefUtil.putJsonStringFromObj(SP_KEY_WINNING_OBJ_JSON, this)
    }

    fun getWinningDetail(): String = "${total}張,中${winning}張"
}