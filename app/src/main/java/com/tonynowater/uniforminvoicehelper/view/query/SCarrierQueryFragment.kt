package com.tonynowater.uniforminvoicehelper.view.query

import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment
import com.tonynowater.uniforminvoicehelper.base.SEmptyPresenter
import kotlinx.android.synthetic.main.fragment_carrier_query.*

/**
 * Created by tonyliao on 2018/4/10.
 */
class SCarrierQueryFragment: SBaseFragment<SEmptyPresenter>() {

    companion object {
        fun newInstance(): SCarrierQueryFragment = SCarrierQueryFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_carrier_query

    override fun initView() {
        val adapter = SCarrierQueryAdapter(childFragmentManager)
        viewpager.adapter = adapter
        tab_layout.setupWithViewPager(viewpager)
    }
}