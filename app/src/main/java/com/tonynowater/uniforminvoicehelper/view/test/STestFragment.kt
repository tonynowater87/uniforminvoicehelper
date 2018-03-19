package com.tonynowater.uniforminvoicehelper.view.test

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Created by tonyliao on 2018/3/13.
 */
class STestFragment : SBaseFragment<STestPresenter>(), View.OnClickListener, STestPresenter.ITestView {

    companion object {
        fun newInstance(): STestFragment = STestFragment()
    }

    private lateinit var adapter: STestAdapter

    override fun onSuccess() {
    }

    override fun onError(msg: String) {
        Toast.makeText(SApplication.context, "onFailure:$msg", Toast.LENGTH_SHORT).show()
    }

    override fun notifyData(entity: List<String>) {
        adapter.addDatas(entity)
    }

    override fun getLayoutId(): Int = R.layout.fragment_test

    override fun initView() {
        mPresenter.attach(this)
        btn_login.setOnClickListener(this)
        initRecyclerView()
    }

    override fun onClick(v: View?) {
        mPresenter.clickTestButton()
    }

    private fun initRecyclerView() {
        adapter = STestAdapter(mPresenter)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}