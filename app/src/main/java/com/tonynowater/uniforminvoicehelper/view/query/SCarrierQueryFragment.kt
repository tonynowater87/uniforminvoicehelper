package com.tonynowater.uniforminvoicehelper.view.query

import android.support.v4.view.ViewPager
import com.tonynowater.datelimitpickerdialog.SDateLimitPickerDialog
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

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    val dialogFragment = SDateLimitPickerDialog()
                    dialogFragment.setListener(object : SDateLimitPickerDialog.OnDatePickerSelectListener {
                        override fun onDatePicked(dateItem: SDateLimitPickerDialog.DateItem) {
                            //選取完自訂的日期區間
                            showToast(dateItem.toString())
                        }
                    })
                    showToast(SDateLimitPickerDialog::class.java.simpleName)
                    dialogFragment.show(fragmentManager, SDateLimitPickerDialog::class.java.simpleName)
                }
            }
        })
    }
}