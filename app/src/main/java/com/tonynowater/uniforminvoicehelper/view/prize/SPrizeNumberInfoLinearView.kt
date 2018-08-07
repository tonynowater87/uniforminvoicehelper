package com.tonynowater.uniforminvoicehelper.view.prize

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseView
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import kotlinx.android.synthetic.main.view_prize_number_info.view.*

/**
 * Created by tonyliao on 2018/8/7.
 */
class SPrizeNumberInfoLinearView @JvmOverloads constructor(context: Context
                                                           , attrs: AttributeSet
                                                           , defStyleAttr: Int = 0) : SBaseView(context, attrs, defStyleAttr) {
    override fun getLayoutResource(): Int = R.layout.view_prize_number_info

    fun setInfo(data: SInvAppPrizeNumListDTO.PrizeNumberDataObject) {
        data?.let {
            tv_prize_number_title.text = it.title
            tv_prize_value.text = it.value
            for (i in 0 until it.number.size) {
                when (i) {
                    0 -> tv_prize_number.text = it.number[i]
                    1 -> {
                        tv_prize_number2.text = it.number[i]
                        tv_prize_number2.visibility = View.VISIBLE
                    }
                    2 -> {
                        tv_prize_number3.text = it.number[i]
                        tv_prize_number3.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    fun setSmallStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv_prize_number.setTextAppearance(R.style.TextAppearance_AppCompat_Small)
        } else {
            tv_prize_number.setTextAppearance(context, R.style.TextAppearance_AppCompat_Small)
        }
    }
}