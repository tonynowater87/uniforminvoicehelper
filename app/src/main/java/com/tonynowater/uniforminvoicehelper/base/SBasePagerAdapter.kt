package com.tonynowater.uniforminvoicehelper.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by tonyliao on 2018/4/10.
 */
abstract class SBasePagerAdapter constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var mBaseViewPagerItems: List<BaseViewPagerItem>

    init {
        mBaseViewPagerItems = initViewPagerItems()
    }

    abstract fun initViewPagerItems(): List<BaseViewPagerItem>

    override fun getItem(position: Int): Fragment = mBaseViewPagerItems[position].m_fragment

    override fun getCount(): Int = mBaseViewPagerItems.size

    override fun getPageTitle(position: Int): CharSequence = mBaseViewPagerItems[position].m_title

    data class BaseViewPagerItem(var m_fragment: Fragment, var m_title: String)
}