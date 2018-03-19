package com.tonynowater.uniforminvoicehelper.view.test

import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseActivity

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestActivity : SBaseActivity<STestPresenter>() {

    override fun getLayoutId(): Int = R.layout.activity_test

    override fun initView() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, STestFragment.newInstance())
                .commit()
    }

    override fun onSuccess() {
    }

    override fun onError(msg: String) {
    }
}