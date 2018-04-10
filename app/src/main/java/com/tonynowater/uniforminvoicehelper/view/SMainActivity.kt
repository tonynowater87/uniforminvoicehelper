package com.tonynowater.uniforminvoicehelper.view

import android.content.Context
import android.content.Intent
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseActivity
import com.tonynowater.uniforminvoicehelper.base.SEmptyPresenter
import com.tonynowater.uniforminvoicehelper.view.query.SCarrierQueryFragment

class SMainActivity : SBaseActivity<SEmptyPresenter>() {

    override fun initView() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SCarrierQueryFragment.newInstance())
                .commit()
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SMainActivity::class.java))
        }
    }
}
