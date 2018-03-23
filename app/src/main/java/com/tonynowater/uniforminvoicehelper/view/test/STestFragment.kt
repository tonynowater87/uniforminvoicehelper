package com.tonynowater.uniforminvoicehelper.view.test

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment
import com.tonynowater.uniforminvoicehelper.data.db.SUserItem
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Created by tonyliao on 2018/3/13.
 */
class STestFragment : SBaseFragment<STestPresenter>(), View.OnClickListener, STestPresenter.ITestView {

    lateinit var mAdapter:STestAdapter

    companion object {
        fun newInstance(): STestFragment = STestFragment()
    }

    override fun onSuccess() {
    }

    override fun onError(msg: String) {
        Toast.makeText(SApplication.mInstance, "onFailure:$msg", Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutId(): Int = R.layout.fragment_test

    override fun initView() {
        mPresenter.attach(this)
        btn_login.setOnClickListener(this)
        btn_get.setOnClickListener(this)
        mAdapter = STestAdapter(mPresenter)
        recycler_view.layoutManager = LinearLayoutManager(SApplication.mInstance)
        recycler_view.adapter = mAdapter
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_login -> {
                mPresenter.clickTestButton(et_user_account.text.toString(), et_password_account.text.toString())
            }
            R.id.btn_get -> {
                mPresenter.getItem()
            }
        }
    }

    override fun notifyData(listItems: List<SUserItem>) {
        mAdapter.removeAllData()
        mAdapter.addDatas(listItems)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.queryAllListItem()
    }
}