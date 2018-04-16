package com.tonynowater.uniforminvoicehelper.view.query

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/4/10.
 */
class SCarrierQueryAdapter @Inject constructor(var mBaseViewPagerItems: List<BaseViewPagerItem>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = mBaseViewPagerItems[position].m_fragment

    override fun getCount(): Int = mBaseViewPagerItems.size

    override fun getPageTitle(position: Int): CharSequence = mBaseViewPagerItems[position].m_title

    data class BaseViewPagerItem(var m_fragment: Fragment, var m_title: String)
}