package com.tonynowater.uniforminvoicehelper.view.prize

import android.support.v7.widget.LinearLayoutManager
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import kotlinx.android.synthetic.main.fragment_prize_number_list.*

/**
 * Created by tonyliao on 2018/3/28.
 */
class SPrizeNumberListFragment : SBaseFragment<SPrizeNumberListPresenter>(), SPrizeNumberListPresenter.IPrizeNumberListView {

    companion object {
        fun newInstance(): SPrizeNumberListFragment = SPrizeNumberListFragment()
    }

    private lateinit var mAdapter: SPrizeNumberListAdapter

    override fun getLayoutId(): Int = R.layout.fragment_prize_number_list

    override fun initView() {
        mPresenter.attach(this)
        mPresenter.getPrizeNumberList()
    }

    override fun showData(dto: SInvAppPrizeNumListDTO) {
        mAdapter = SPrizeNumberListAdapter(dto)
        recycler_view.layoutManager = LinearLayoutManager(SApplication.mInstance)
        recycler_view.adapter = mAdapter
    }
}