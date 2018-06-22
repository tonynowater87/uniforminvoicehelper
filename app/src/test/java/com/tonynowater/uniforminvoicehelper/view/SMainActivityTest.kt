package com.tonynowater.uniforminvoicehelper.view

import android.support.design.widget.BottomNavigationView
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SBaseRobolectricTestCase
import com.tonynowater.uniforminvoicehelper.view.input.SManualInputNumberFragment
import com.tonynowater.uniforminvoicehelper.view.prize.SPrizeNumberListFragment
import com.tonynowater.uniforminvoicehelper.view.query.SCarrierQueryFragment
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

/**
 * Created by tonyliao on 2018/4/18.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25])
class SMainActivityTest : SBaseRobolectricTestCase() {

    val tag = SMainActivityTest::class.java.simpleName

    private lateinit var mainActivity: SMainActivity

    @Before
    override fun setUp() {
        super.setUp()
        mainActivity = Robolectric.setupActivity(SMainActivity::class.java)
    }

    /**
     * 測試點擊 BottomNavigation，有切換正確的 Fragment
     */
    @Test
    fun testNavigationBottomToQueryCarrierInvoice() {
        mainActivity.findViewById<BottomNavigationView>(R.id.bottom_navigation_view).selectedItemId = R.id.menu_item_query_carrier_invoice
        val fragment = mainActivity.supportFragmentManager.findFragmentByTag(SCarrierQueryFragment::class.java.simpleName)
        ShadowLog.d(tag, fragment?.toString())
        assertNotNull(fragment)
    }

    @Test
    fun testNavigationBottomToInvoicePrizeNumbers() {
        mainActivity.findViewById<BottomNavigationView>(R.id.bottom_navigation_view).selectedItemId = R.id.menu_item_invoice_prize_numbers
        val fragment = mainActivity.supportFragmentManager.findFragmentByTag(SPrizeNumberListFragment::class.java.simpleName)
        ShadowLog.d(tag, fragment?.toString())
        assertNotNull(fragment)
    }

    @Test
    fun testNavigationBottomToManualInputNumber() {
        mainActivity.findViewById<BottomNavigationView>(R.id.bottom_navigation_view).selectedItemId = R.id.menu_item_manual_input_number
        val fragment = mainActivity.supportFragmentManager.findFragmentByTag(SManualInputNumberFragment::class.java.simpleName)
        ShadowLog.d(tag, fragment?.toString())
        assertNotNull(fragment)
    }
}