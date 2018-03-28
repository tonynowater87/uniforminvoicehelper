package com.tonynowater.uniforminvoicehelper.view.prize

import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment

/**
 * Created by tonyliao on 2018/3/28.
 */
class SPrizeNumberListFragment: SBaseFragment<SPrizeNumberListPresenter>() {

    companion object {
        fun newInstance(): SPrizeNumberListFragment = SPrizeNumberListFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_prize_number_list

    override fun initView() {
        mPresenter.getPrizeNumberList()
    }
}