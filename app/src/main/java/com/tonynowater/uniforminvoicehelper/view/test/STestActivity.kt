package com.tonynowater.uniforminvoicehelper.view.test

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.tonynowater.uniforminvoicehelper.BuildConfig
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseActivity
import kotlinx.android.synthetic.main.activity_test.*

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestActivity : SBaseActivity<STestPresenter>(), View.OnClickListener, STestPresenter.ITestView {

    private lateinit var adapter: STestAdapter

    override fun getLayoutId(): Int = R.layout.activity_test

    override fun initView() {
        mPresenter.attach(this)
        btn_login.setOnClickListener(this)
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        et_user_account.setText(BuildConfig.TestAccount)
        et_password_account.setText(BuildConfig.TestPassword)
    }

    override fun onClick(v: View?) {
        mPresenter.clickTestButton()
    }

    override fun notifyData(entity: List<String>) {
        adapter.addDatas(entity)
    }

    private fun initRecyclerView() {
        adapter = STestAdapter(mPresenter)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onSuccess() {

    }

    override fun onError(msg: String) {
        Toast.makeText(SApplication.context, "onFailure:$msg", Toast.LENGTH_SHORT).show()
    }
}