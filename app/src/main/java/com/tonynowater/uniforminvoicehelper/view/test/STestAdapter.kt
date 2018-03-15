package com.tonynowater.uniforminvoicehelper.view.test

import android.widget.TextView
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.api.dto.SCarrierInvoiceHeaderDetailDTO
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestAdapter(m_onClickItemListener: OnClickItemListener<SCarrierInvoiceHeaderDetailDTO>) : SBaseRecyclerViewAdapter<SCarrierInvoiceHeaderDetailDTO>(m_onClickItemListener) {

    override fun bindView(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_invoice_info)?.text = m_data[position].showedText()
    }

    override val layoutResource: Int = R.layout.list_test
}