package com.tonynowater.uniforminvoicehelper.view.login

import android.view.View
import com.tonynowater.uniforminvoicehelper.BuildConfig
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment
import com.tonynowater.uniforminvoicehelper.view.SMainActivity
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by tonyliao on 2018/4/18.
 */
class SLoginFragment: SBaseFragment<SLoginPresenter>(), View.OnClickListener {

    companion object {
        fun newInstance() = SLoginFragment()
    }

    override fun onClick(v: View?) {
        mPresenter.login(et_user_account.text.toString(), et_password_account.text.toString())
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initView() {
        mPresenter.attach(this)
        button_login.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        et_user_account.setText(BuildConfig.TestAccount)
        et_password_account.setText(BuildConfig.TestPassword)
    }

    override fun showSuccess() {
        SMainActivity.start(SApplication.mInstance)
    }

    override fun showError(msg: String) {
        showToast(msg)
    }
}