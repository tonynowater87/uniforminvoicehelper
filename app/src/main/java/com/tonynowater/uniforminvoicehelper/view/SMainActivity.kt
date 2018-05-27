package com.tonynowater.uniforminvoicehelper.view

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseActivity
import com.tonynowater.uniforminvoicehelper.base.SEmptyPresenter
import com.tonynowater.uniforminvoicehelper.util.SBottomNavigationHelper
import com.tonynowater.uniforminvoicehelper.view.input.SManualInputNumberFragment
import com.tonynowater.uniforminvoicehelper.view.prize.SPrizeNumberListFragment
import com.tonynowater.uniforminvoicehelper.view.query.SCarrierQueryFragment
import kotlinx.android.synthetic.main.activity_main.*

class SMainActivity : SBaseActivity<SEmptyPresenter>() {

    override fun initView() {
        bottom_navigation_view.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_item_invoice_prize_numbers -> {
                    replaceFragment(SPrizeNumberListFragment.newInstance())
                    true
                }
                R.id.menu_item_query_carrier_invoice -> {
                    replaceFragment(SCarrierQueryFragment.newInstance())
                    true
                }
                R.id.menu_item_manual_input_number -> {
                    replaceFragment(SManualInputNumberFragment())
                    true
                }
                R.id.menu_item_setting -> {
                    false
                }
                else -> {
                    false
                }
            }
        }
        bottom_navigation_view.selectedItemId = R.id.menu_item_invoice_prize_numbers
        SBottomNavigationHelper.disableShiftMode(bottom_navigation_view)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SMainActivity::class.java))
        }
    }
}
