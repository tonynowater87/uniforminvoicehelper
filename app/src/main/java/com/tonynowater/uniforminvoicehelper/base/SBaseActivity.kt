package com.tonynowater.uniforminvoicehelper.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
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
    private var mToast: Toast? = null

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
        mPresenter.detach()
        super.onDestroy()
    }

    override fun showSuccess() {
    }

    override fun showError(msg: String) {
    }

    override fun showLoading() {
        mProgressDialog.show()
    }

    override fun hideLoading() {
        mProgressDialog.dismiss()
    }

    override fun showToast(msg: String) {
        if (mToast != null) {
            mToast!!.cancel()
        }

        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        mToast!!.show()
    }

    protected fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment::class.java.simpleName)
                .commit()
    }
}