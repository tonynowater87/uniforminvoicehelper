package com.tonynowater.uniforminvoicehelper.view.query

import android.widget.TextView
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceHeaderDTO

/**
 * Created by tonyliao on 2018/4/10.
 */
class SCarrierQueryListAdapter constructor(listener: OnClickItemListener<SCarrierInvoiceHeaderDTO>) : SBaseRecyclerViewAdapter<SCarrierInvoiceHeaderDTO>(listener) {

    override fun bindView(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_header_date)?.text = m_data[position].date
        holder.getView<TextView>(R.id.tv_header_invoice_number)?.text = m_data[position].invoiceNo
        holder.getView<TextView>(R.id.tv_header_cost)?.text = m_data[position].amount
        holder.getView<TextView>(R.id.tv_header_seller)?.text = m_data[position].sellerName
    }

    override val layoutResource = R.layout.list_carrier_query_header
}