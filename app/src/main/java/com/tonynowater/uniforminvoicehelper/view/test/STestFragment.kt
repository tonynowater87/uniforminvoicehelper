package com.tonynowater.uniforminvoicehelper.view.test

import android.appwidget.AppWidgetManager
import android.view.View
import android.widget.Toast
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment
import com.tonynowater.uniforminvoicehelper.util.SBarCodeImageGenerator
import com.tonynowater.uniforminvoicehelper.view.component.SBarCodeView
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Created by tonyliao on 2018/3/13.
 */
class STestFragment : SBaseFragment<STestPresenter>(), View.OnClickListener, STestPresenter.ITestView {

    companion object {
        fun newInstance(): STestFragment = STestFragment()
    }

    override fun showBarCode(barCodeItem: SBarCodeImageGenerator.BarCodeItem) {
        image_view.setBarCodeItem(barCodeItem)
    }

    override fun onSuccess() {
    }

    override fun onError(msg: String) {
        Toast.makeText(SApplication.context, "onFailure:$msg", Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutId(): Int = R.layout.fragment_test

    override fun initView() {
        mPresenter.attach(this)
        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        mPresenter.clickTestButton(et_user_account.text.toString(), et_password_account.text.toString())
    }

    override fun getBarcodeView(): SBarCodeView = image_view
}