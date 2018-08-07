package com.tonynowater.uniforminvoicehelper.view.prize

import android.content.Context
import android.util.AttributeSet
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseView
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import kotlinx.android.synthetic.main.view_prize_number_list.view.*

/**
 * Created by tonyliao on 2018/8/7.
 */
class SPrizeNumberView @JvmOverloads constructor(context: Context
                                                 , attrs: AttributeSet
                                                 , defStyleAttr: Int = 0) : SBaseView(context, attrs, defStyleAttr) {
    override fun getLayoutResource(): Int = R.layout.view_prize_number_list

    fun setDTO(dto: SInvAppPrizeNumListDTO) {
        tv_prize_number_list_term.text = dto.getInvoiceTermText()
        val e = SInvAppPrizeNumListDTO.PrizeType.values()
        for (i in 0..e.size) {
            val it = e[i]
            when (it) {
                SInvAppPrizeNumListDTO.PrizeType.SUPER -> super_prize_info_view.setInfo(dto.getDataObject(it))
                SInvAppPrizeNumListDTO.PrizeType.SPECIAL -> special_prize_info_view.setInfo(dto.getDataObject(it))
                SInvAppPrizeNumListDTO.PrizeType.FIRST -> first_prize_info_view.setInfo(dto.getDataObject(it))
                SInvAppPrizeNumListDTO.PrizeType.SECOND -> {
                    second_prize_info_view.setInfo(dto.getDataObject(it))
                    second_prize_info_view.setSmallStyle()
                }
                SInvAppPrizeNumListDTO.PrizeType.THIRD -> {
                    third_prize_info_view.setInfo(dto.getDataObject(it))
                    third_prize_info_view.setSmallStyle()
                }
                SInvAppPrizeNumListDTO.PrizeType.FOURTH -> {
                    fourth_prize_info_view.setInfo(dto.getDataObject(it))
                    fourth_prize_info_view.setSmallStyle()
                }
                SInvAppPrizeNumListDTO.PrizeType.FIFTH -> {
                    fifth_prize_info_view.setInfo(dto.getDataObject(it))
                    fifth_prize_info_view.setSmallStyle()
                }
                SInvAppPrizeNumListDTO.PrizeType.SIXTH -> {
                    sixth_prize_info_view.setInfo(dto.getDataObject(it))
                    sixth_prize_info_view.setSmallStyle()
                }
                SInvAppPrizeNumListDTO.PrizeType.ADDITION_SIXTH -> addition_sixth_prize_info_view.setInfo(dto.getDataObject(it))
            }
        }
    }
}