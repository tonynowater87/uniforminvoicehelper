package com.tonynowater.uniforminvoicehelper.view.test

import android.widget.TextView
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter
import com.tonynowater.uniforminvoicehelper.data.db.SUserEntity

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestAdapter(m_onClickEntityListener: OnClickItemListener<SUserEntity>) : SBaseRecyclerViewAdapter<SUserEntity>(m_onClickEntityListener) {

    override fun bindView(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_invoice_info).text = m_data[position].account
    }

    override val layoutResource: Int = R.layout.list_test
}