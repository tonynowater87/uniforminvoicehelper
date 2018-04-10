package com.tonynowater.uniforminvoicehelper.view.query

import android.support.v4.app.FragmentManager
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBasePagerAdapter

/**
 * Created by tonyliao on 2018/4/10.
 */
class SCarrierQueryAdapter constructor(fm: FragmentManager) : SBasePagerAdapter(fm) {
    override fun initViewPagerItems(): List<BaseViewPagerItem> {
        return listOf(BaseViewPagerItem(SCarrierQueryListFragment.newInstance(ECarrierQueryType.THIS_MONTH), SApplication.getString(R.string.this_month))
                    , BaseViewPagerItem(SCarrierQueryListFragment.newInstance(ECarrierQueryType.LAST_MONTH), SApplication.getString(R.string.last_month))
                    , BaseViewPagerItem(SCarrierQueryListFragment.newInstance(ECarrierQueryType.CUSTOM_MONTH), SApplication.getString(R.string.custom_month))
                    , BaseViewPagerItem(SCarrierQueryListFragment.newInstance(ECarrierQueryType.PRIZE_RECORD), SApplication.getString(R.string.prize_record)))
    }
}