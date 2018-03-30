package com.tonynowater.uniforminvoicehelper.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tonynowater.uniforminvoicehelper.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/19.
 */
abstract class SBaseFragment<P : SBasePresenter<*, *>> : DaggerFragment(), IBaseView {

    @Inject
    lateinit var mPresenter: P

    private lateinit var mProgressDialog: ProgressDialog
    private var mToast: Toast? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initProgressDialog()
    }

    override fun onDetach() {
        mPresenter.detach()
        super.onDetach()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    private fun initProgressDialog() {
        mProgressDialog = ProgressDialog(context)
        mProgressDialog.setTitle(getString(R.string.app_name))
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
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

        mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        mToast!!.show()
    }
}