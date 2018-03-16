package com.tonynowater.uniforminvoicehelper.base

import android.app.ProgressDialog
import android.os.Bundle
import com.tonynowater.uniforminvoicehelper.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
abstract class SBaseActivity<P : SBasePresenter<*, *>> :DaggerAppCompatActivity(), IBaseView {

    @Inject
    lateinit var mPresenter: P

    private lateinit var mProgressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initProgressDialog()
        initView()
    }

    abstract fun initView()

    abstract fun getLayoutId(): Int

    private fun initProgressDialog() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle(getString(R.string.app_name))
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detach()
    }

    override fun showLoading() {
        mProgressDialog.show()
    }

    override fun hideLoading() {
        mProgressDialog.dismiss()
    }

    override fun onNoData() {

    }
}