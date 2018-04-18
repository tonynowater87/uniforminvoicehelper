package com.tonynowater.uniforminvoicehelper.view.login

import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseActivity
import com.tonynowater.uniforminvoicehelper.base.SEmptyPresenter

/**
 * Created by tonyliao on 2018/2/27.
 */
class SLoginActivity : SBaseActivity<SEmptyPresenter>() {

    override fun initView() {
        replaceFragment(SLoginFragment.newInstance())
    }

    override fun getLayoutId(): Int = R.layout.activity_login
}