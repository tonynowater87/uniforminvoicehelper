package com.tonynowater.uniforminvoicehelper.view.prize

import android.widget.TextView
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO

/**
 * Created by tonyliao on 2018/3/30.
 */
class SPrizeNumberListAdapter(val mDto: SInvAppPrizeNumListDTO) : SBaseRecyclerViewAdapter<SPrizeNumberItem>() {

    init {
        val mPrizeNameArray = SApplication.mInstance.resources.getStringArray(R.array.prize_name_array).toMutableList()
        val mPrizeNameDescribeArray = SApplication.mInstance.resources.getStringArray(R.array.prize_description_array).toMutableList()
        val items = mutableListOf<SPrizeNumberItem>()
        for (i in 0 until mPrizeNameArray.size) {
            items.add(i, SPrizeNumberItem(mPrizeNameArray[i], mPrizeNameDescribeArray[i]))
        }

        m_data = items.toMutableList()
    }

    override fun bindView(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_prize_amt_name)?.text = m_data[position].name
        holder.getView<TextView>(R.id.tv_prize_amount_descript)?.text = m_data[position].description

        when (position) {
            0 -> {
                holder.getView<TextView>(R.id.tv_prize_number)?.text = mDto.superPrizeNo
            }
            1 -> {
                holder.getView<TextView>(R.id.tv_prize_number)?.text = mDto.getSpcPrizeNumbers()
            }
            2 -> {
                holder.getView<TextView>(R.id.tv_prize_number)?.text = mDto.getFirstPrizeNumbers()
            }
            8 -> {
                holder.getView<TextView>(R.id.tv_prize_number)?.text = mDto.getSecondToSixthPrizeNumbers(SInvAppPrizeNumListDTO.PrizeType.ADDITION_SIXTH)
            }
        }
    }

    override val layoutResource = R.layout.list_prize_number
}