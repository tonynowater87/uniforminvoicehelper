package com.tonynowater.uniforminvoicehelper.view

import android.view.View
import android.widget.Toast
import com.tonynowater.uniforminvoicehelper.BuildConfig
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by tonyliao on 2018/2/27.
 */
class SLoginActivity : SBaseActivity<SLoginPresenter>(), View.OnClickListener {

    private val TAG = this@SLoginActivity.javaClass.simpleName

    override fun onClick(v: View?) {
        SMainActivity.start(this)
    }

    override fun initView() {
        mPresenter.attach(this)
        button.setOnClickListener(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun onResume() {
        super.onResume()
        et_user_account.setText(BuildConfig.TestAccount)
        et_password_account.setText(BuildConfig.TestPassword)
    }

    override fun onSuccess() {
        Toast.makeText(SApplication.mInstance, "onSuccess", Toast.LENGTH_SHORT).show()
    }

    override fun onError(msg: String) {
        Toast.makeText(SApplication.mInstance, "onFailure:$msg", Toast.LENGTH_SHORT).show()
    }
}